package com.thinking.hello.AnnotationConfigApplicationContext_001;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestApp {
    @Test
    public void test(){
        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        User user1=context.getBean(User.class);
        User user2=(User)context.getBean("my_test_user");
        System.out.println("user1-->"+user1.getUserName());
        System.out.println("user2-->"+user2.getUserName());
    }
}
