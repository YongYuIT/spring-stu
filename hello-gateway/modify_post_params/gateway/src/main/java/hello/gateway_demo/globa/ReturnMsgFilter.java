package hello.gateway_demo.globa;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ReturnMsgFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String testPath = exchange.getRequest().getPath().toString();
        if (testPath.endsWith("/yong") || testPath.endsWith("/test_post_modify")) {
            return chain.filter(exchange);
        } else {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            DataBuffer buffer = response.bufferFactory().wrap("fuck you".getBytes());
            return response.writeWith(Flux.just(buffer));
        }
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
