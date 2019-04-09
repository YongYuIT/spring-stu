package com.thinking.hello.lifecycle_005;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestApp {
    @Test
    public void Test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(IoCConfig.class);
        System.out.println("--------------------------------------------going to use bean");

        MyBean bean = context.getBean(MyBean.class);
        ((AnnotationConfigApplicationContext) context).close();

        System.out.println("--------------------------------------------over");
    }
}
