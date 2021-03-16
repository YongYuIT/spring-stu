package com.thinking.helloscheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HelloScheduledApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloScheduledApplication.class, args);
    }

}
