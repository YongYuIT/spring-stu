package com.thinking.hello.lifecycle_005;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MyBean  implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("--------------------------------------------setBeanFactory");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("--------------------------------------------setBeanName");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("--------------------------------------------destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--------------------------------------------afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("--------------------------------------------setApplicationContext");
    }

    //===========================================================

    @PostConstruct
    public void myInit(){
        System.out.println("--------------------------------------------myInit");
    }

    @PreDestroy
    public void myDestroy(){
        System.out.println("--------------------------------------------myDestroy");
    }
}
