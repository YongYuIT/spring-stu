package hello.filter.global;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Service
public class RateLimterKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        //limit by IP
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        System.out.println("ip-->" + ip);
        return Mono.just(ip);
    }
}
