package com.hello.helloAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello-api")
public class HelloController {
    @GetMapping(value = "/sayhello/{name}")
    public HelloResponse sayHello(@PathVariable("name") String name) {
        HelloResponse response = new HelloResponse();
        response.setRespMsg("hello --> " + name);
        return response;
    }
}
