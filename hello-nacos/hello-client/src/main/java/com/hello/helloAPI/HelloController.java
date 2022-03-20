package com.hello.helloAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello-api")
public class HelloController {

    @Autowired
    HelloInterface helloInterface;

    @GetMapping(value = "/sayhello/{name}")
    public HelloResponse sayHello(@PathVariable("name") String name) {
        HelloResponse response = new HelloResponse();
        name = name + "-->" + helloInterface.sayHello();
        response.setRespMsg("hello --> " + name);
        return response;
    }
}
