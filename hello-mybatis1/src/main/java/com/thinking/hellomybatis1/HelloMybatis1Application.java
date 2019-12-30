package com.thinking.hellomybatis1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.thinking.hellomybatis1.mapper")
public class HelloMybatis1Application {

    public static void main(String[] args) {
        SpringApplication.run(HelloMybatis1Application.class, args);
    }

}
