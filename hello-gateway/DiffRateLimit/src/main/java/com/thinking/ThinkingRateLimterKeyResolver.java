package com.thinking;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ThinkingRateLimterKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String pathParams = exchange.getRequest().getPath().toString().split("/")[2];
        System.out.println("get pathParams-->" + pathParams);
        return Mono.just(pathParams);
    }
}
