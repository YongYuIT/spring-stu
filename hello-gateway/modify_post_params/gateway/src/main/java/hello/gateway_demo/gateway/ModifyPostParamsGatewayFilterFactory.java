package hello.gateway_demo.gateway;

import io.netty.buffer.ByteBufAllocator;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Service
public class ModifyPostParamsGatewayFilterFactory extends AbstractGatewayFilterFactory/*ModifyRequestBodyGatewayFilterFactory*/ {
    @Override
    public GatewayFilter apply(Object config) {
        return modifyFilter;
    }

    GatewayFilter modifyFilter = (exchange, chain) -> {
        System.out.println("start ModifyPostParamsGatewayFilterFactory");
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        HttpMethod method = serverHttpRequest.getMethod();
        String contentType = serverHttpRequest.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
        System.out.println("method-->" + method + "; contentType-->" + contentType);
        if (method == HttpMethod.POST && (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equalsIgnoreCase(contentType)
                || MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType))) {
            System.out.println("post json, need to modify");

            String bodyStr = exchange.getAttribute("cachedRequestBodyObject");
            System.out.println("old req body-->" + bodyStr);
            bodyStr = "{\n" +
                    "    \"name\": \"yong1\",\n" +
                    "    \"age\": 301\n" +
                    "}";
            System.out.println("new req body-->" + bodyStr);

            //下面的将请求体再次封装写回到request里，传到下一级，否则，由于请求体已被消费，后续的服务将取不到值
            URI uri = serverHttpRequest.getURI();
            URI newUri = UriComponentsBuilder.fromUri(uri).build(true).toUri();
            ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();
            DataBuffer bodyDataBuffer = stringBuffer(bodyStr);
            Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);

            // 定义新的消息头
            HttpHeaders headers = new HttpHeaders();
            headers.putAll(exchange.getRequest().getHeaders());

            // 由于修改了传递参数，需要重新设置CONTENT_LENGTH，长度是字节长度，不是字符串长度
            int length = bodyStr.getBytes().length;
            headers.remove(HttpHeaders.CONTENT_LENGTH);
            headers.setContentLength(length);

            // 设置CONTENT_TYPE
            if (!StringUtils.isEmpty(contentType)) {
                headers.set(HttpHeaders.CONTENT_TYPE, contentType);
            }

            // 由于post的body只能订阅一次，由于上面代码中已经订阅过一次body。所以要再次封装请求到request才行，不然会报错请求已经订阅过
            request = new ServerHttpRequestDecorator(request) {
                @Override
                public HttpHeaders getHeaders() {
                    long contentLength = headers.getContentLength();
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.putAll(super.getHeaders());
                    if (contentLength > 0) {
                        httpHeaders.setContentLength(contentLength);
                    } else {
                        httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                    }
                    return httpHeaders;
                }

                @Override
                public Flux<DataBuffer> getBody() {
                    return bodyFlux;
                }
            };
            //封装request，传给下一级
            request.mutate().header(HttpHeaders.CONTENT_LENGTH, Integer.toString(bodyStr.length()));
            return chain.filter(exchange.mutate().request(request).build());
        } else {
            return chain.filter(exchange);
        }
    };


    private DataBuffer stringBuffer(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }


}
