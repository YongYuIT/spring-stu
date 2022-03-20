package com.hello.helloAPI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("test-hello-svc")
public interface HelloInterface {
    @RequestMapping(method = RequestMethod.GET, value = "/yong-test/hello-api/sayhello/yong")
    String sayHello();
}
