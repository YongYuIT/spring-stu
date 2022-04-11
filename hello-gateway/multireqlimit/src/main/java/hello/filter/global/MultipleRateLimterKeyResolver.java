package hello.filter.global;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Service
public class MultipleRateLimterKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String name = request.getQueryParams().get("name").get(0);
        String gen = request.getQueryParams().get("gen").get(0);
        String con = request.getQueryParams().get("con").get(0);
        System.out.println("name-->" + name + ", gen-->" + gen + ", con-->" + con);
        return Mono.just(name + "##" + gen + "##" + con);
    }
}
