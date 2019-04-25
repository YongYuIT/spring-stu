package com.thinking.helloamqpretry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rabbit_test")
public class MyController {

    @Autowired
    private RabbitService service;

    @PostMapping("/testMsg")
    public String testMsg(@RequestBody String msg){
        service.sendMsg(msg);
        return "sent meg";
    }

    @PostMapping("/testUser")
    public String testUser(@RequestBody  User user){
        service.sendUser(user);
        return "sent user";
    }
}
