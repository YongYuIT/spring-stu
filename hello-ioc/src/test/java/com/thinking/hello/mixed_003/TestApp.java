package com.thinking.hello.mixed_003;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestApp {
    @Test
    public void test(){
        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        User user1=context.getBean(User.class);
        Client client1=(Client)context.getBean(Client.class);
        System.out.println("user1-->"+user1.getUserName());
        System.out.println("client1-->"+client1.getClientName());
    }
}
