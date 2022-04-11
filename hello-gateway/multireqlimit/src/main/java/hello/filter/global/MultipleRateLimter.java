package hello.filter.global;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class MultipleRateLimter extends RedisRateLimiter {

    public MultipleRateLimter(ReactiveStringRedisTemplate redisTemplate, RedisScript<List<Long>> script, ConfigurationService configurationService) {
        super(redisTemplate, script, configurationService);
    }


    /**
     * simple implements
     */
//    @Override
//    @SuppressWarnings("unchecked")
//    public Mono<Response> isAllowed(String routeId, String id) {
//        String[] ids = id.split("##");
//        return super.isAllowed(routeId, ids[0]).flatMap(response -> {
//            if (!response.isAllowed()) {
//                return Mono.just(response);
//            } else {
//                return MultipleRateLimter.super.isAllowed(routeId, ids[1]);
//            }
//
//        });
//    }
    @Override
    @SuppressWarnings("unchecked")
    public Mono<Response> isAllowed(String routeId, String id) {
        String[] ids = id.split("##");
        Mono<Response> thisMono = super.isAllowed(routeId, ids[0]);
        for (int i = 0; i < ids.length; i++) {
            if (i + 1 < ids.length) {
                thisMono = replaceWithNext(thisMono, routeId, ids[i + 1]);
            }
        }
        return thisMono;
    }

    public Mono<Response> replaceWithNext(Mono<Response> current, String routeId, String nextId) {
        return current.flatMap(response -> {
            //got limit and don't need to replace current
            if (!response.isAllowed()) {
                return Mono.just(response);
            }
            //no limit, and replace current
            else {
                return MultipleRateLimter.super.isAllowed(routeId, nextId);
            }
        });
    }

}
