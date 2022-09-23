package com.thinking;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteDefinitionRouteLocator;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ThinkingRateLimter extends RedisRateLimiter {

    private RedisScript<List<Long>> script;
    private ReactiveStringRedisTemplate redisTemplate;
    private AtomicBoolean initialized = new AtomicBoolean(false);

    public ThinkingRateLimter(ReactiveStringRedisTemplate redisTemplate, RedisScript<List<Long>> script, ConfigurationService configurationService) {
        super(redisTemplate, script, configurationService);
        this.redisTemplate = redisTemplate;
        this.script = script;
        this.initialized.compareAndSet(false, true);
    }

    private Config defaultConfig;

    static List<String> getKeys(String id) {
        // use `{}` around keys to use Redis Key hash tags
        // this allows for using redis cluster

        // Make a unique key per user.
        String prefix = "request_rate_limiter.{" + id;

        // You need two Redis keys for Token Bucket.
        String tokenKey = prefix + "}.tokens";
        String timestampKey = prefix + "}.timestamp";
        return Arrays.asList(tokenKey, timestampKey);
    }


    @Override
    public Mono<Response> isAllowed(String routeId, String id) {
        if (!this.initialized.get()) {
            throw new IllegalStateException("RedisRateLimiter is not initialized");
        }

        //by thinking
        //Config routeConfig = loadConfiguration(routeId);
        Config routeConfig = loadConfiguration(routeId, id);

        // How many requests per second do you want a user to be allowed to do?
        int replenishRate = routeConfig.getReplenishRate();

        // How much bursting do you want to allow?
        int burstCapacity = routeConfig.getBurstCapacity();

        // How many tokens are requested per request?
        int requestedTokens = routeConfig.getRequestedTokens();

        try {
            List<String> keys = getKeys(id);

            // The arguments to the LUA script. time() returns unixtime in seconds.
            List<String> scriptArgs = Arrays.asList(replenishRate + "", burstCapacity + "", "", requestedTokens + "");
            // allowed, tokens_left = redis.eval(SCRIPT, keys, args)
            Flux<List<Long>> flux = this.redisTemplate.execute(this.script, keys, scriptArgs);
            // .log("redisratelimiter", Level.FINER);
            return flux.onErrorResume(throwable -> {

                System.out.println("Error calling rate limiter lua" + throwable.getMessage());

                return Flux.just(Arrays.asList(1L, -1L));
            }).reduce(new ArrayList<Long>(), (longs, l) -> {
                longs.addAll(l);
                return longs;
            }).map(results -> {
                boolean allowed = results.get(0) == 1L;
                Long tokensLeft = results.get(1);

                Response response = new Response(allowed, getHeaders(routeConfig, tokensLeft));

                System.out.println("response: " + response);

                return response;
            });
        } catch (Exception e) {
            /*
             * We don't want a hard dependency on Redis to allow traffic. Make sure to set
             * an alert so you know if this is happening too much. Stripe's observed
             * failure rate is 0.01%.
             */
            System.out.println("Error determining if user allowed from redis" + e.getMessage());
        }
        return Mono.just(new Response(true, getHeaders(routeConfig, -1L)));
    }

    boolean isAComed = false;

    Config loadConfiguration(String routeId, String id) {
        Config routeConfig = getConfig().getOrDefault(routeId, defaultConfig);

        if (routeConfig == null) {
            routeConfig = getConfig().get(RouteDefinitionRouteLocator.DEFAULT_FILTERS);
        }

        if (routeConfig == null) {
            throw new IllegalArgumentException("No Configuration found for route " + routeId + " or defaultFilters");
        }


        if (id.equalsIgnoreCase("aaa")) {
            if (isAComed) {
                routeConfig.setBurstCapacity(20);
                routeConfig.setReplenishRate(10);
            } else {
                routeConfig.setBurstCapacity(40);
                routeConfig.setReplenishRate(20);
            }
            isAComed = true;
        } else if (id.equalsIgnoreCase("bbb")) {
            routeConfig.setBurstCapacity(30);
            routeConfig.setReplenishRate(15);
        }


        return routeConfig;
    }

    Config loadConfiguration(String routeId) {
        Config routeConfig = getConfig().getOrDefault(routeId, defaultConfig);

        if (routeConfig == null) {
            routeConfig = getConfig().get(RouteDefinitionRouteLocator.DEFAULT_FILTERS);
        }

        if (routeConfig == null) {
            throw new IllegalArgumentException("No Configuration found for route " + routeId + " or defaultFilters");
        }
        return routeConfig;
    }

}
