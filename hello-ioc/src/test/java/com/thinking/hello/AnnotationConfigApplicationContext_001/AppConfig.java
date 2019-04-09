package com.thinking.hello.AnnotationConfigApplicationContext_001;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean(name="my_test_user")
    public  User createFuckUser(){
        User user=new User();
        user.setId(1L);
        user.setNote("aaa");
        user.setUserName("name_111");
        return user;
    }
}
