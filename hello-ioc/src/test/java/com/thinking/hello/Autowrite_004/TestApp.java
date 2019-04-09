package com.thinking.hello.Autowrite_004;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestApp {
    @Test
     public void test(){
        ApplicationContext context= new AnnotationConfigApplicationContext(AppConfig.class);
        House house=context.getBean(House.class);
        house.doSomeThing();
        System.out.println("----------------");
        House house1=new House();
        house.doSomeThing();
    }
}
