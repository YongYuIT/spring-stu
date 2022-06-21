package com.thinking.degrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DegradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(DegradeApplication.class, args);
        System.out.println("main run success...");
    }
}