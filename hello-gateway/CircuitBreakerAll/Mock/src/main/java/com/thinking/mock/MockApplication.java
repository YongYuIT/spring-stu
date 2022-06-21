package com.thinking.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockApplication {
    public static void main(String[] args) {
        SpringApplication.run(MockApplication.class, args);
        System.out.println("main run success...");
    }
}