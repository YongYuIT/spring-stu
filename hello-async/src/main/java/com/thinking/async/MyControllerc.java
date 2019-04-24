package com.thinking.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyControllerc {

    @Autowired
    private MyAsyncService service;

    @GetMapping("/test_async")
    public String testAsync() {
        service.myAsyncTask();
        return "myAsyncTask running";
    }
}
