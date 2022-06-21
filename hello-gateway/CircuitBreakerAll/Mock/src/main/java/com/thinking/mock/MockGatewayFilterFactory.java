package com.thinking.mock;

import com.alibaba.fastjson.JSON;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@Component
public class MockGatewayFilterFactory extends AbstractGatewayFilterFactory {
    @Override
    public GatewayFilter apply(Object config) {
        return mockFilter;
    }

    GatewayFilter mockFilter = (exchange, chain) -> {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String uri = request.getURI().toString();

        System.out.println("MockGatewayFilterFactory header-->" + JSON.toJSONString(request.getHeaders()));
        System.out.println("MockGatewayFilterFactory url-->" + uri);
        System.out.println("MockGatewayFilterFactory body-->" + JSON.toJSONString(exchange.getAttribute("cachedRequestBodyObject")));

        if (uri.endsWith("exp")) {
            throw new RuntimeException("fuck exp");
        }

        Map resp = new HashMap<>();
        resp.put("code", 200);
        resp.put("msg", "test success");

        DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONString(resp).getBytes());
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Flux.just(buffer));
    };
}
