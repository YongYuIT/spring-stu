package com.thinking.hellomybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.thinking.hellomybatis.mapper")
public class HelloMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloMybatisApplication.class, args);
    }

}
