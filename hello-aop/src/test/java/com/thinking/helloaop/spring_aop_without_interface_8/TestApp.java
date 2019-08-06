package com.thinking.helloaop.spring_aop_without_interface_8;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan
@EnableAspectJAutoProxy //这个必须要加,否则AOP不起作用
public class TestApp {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApp.class);
        Dog dog = context.getBean(Dog.class);
        dog.sayName("fuckUUU");
    }
}

