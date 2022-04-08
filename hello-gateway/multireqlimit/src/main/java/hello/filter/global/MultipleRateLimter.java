package hello.filter.global;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteDefinitionRouteLocator;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MultipleRateLimter extends RedisRateLimiter {

    public MultipleRateLimter(ReactiveStringRedisTemplate redisTemplate, RedisScript<List<Long>> script, ConfigurationService configurationService) {
        super(redisTemplate, script, configurationService);

    }

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Response> isAllowed(String routeId, String id) {
        String[] ids = id.split("##");
        Mono<Response> allMono = null;
        for (String sid : ids) {
            Mono<Response> thisMono = super.isAllowed(routeId, sid);
            if (allMono == null) {
                allMono = thisMono;
            } else {
                allMono.and(thisMono);
            }
        }
        return allMono;
    }

}