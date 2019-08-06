package com.thinking.helloaop.spring_aop_multi_asprct_9;

import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(3)
public class AnimalAspect2 {

    @Pointcut("execution(* com.thinking.helloaop.spring_aop_multi_asprct_9.Dog.sayName(..))")
    public void pointCut() {
        System.out.println("do pointCut  2222");
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("do before  2222");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("do after  2222");
    }

    @AfterReturning("pointCut()")
    public void afterReturing() {
        System.out.println("do afterReturing  2222");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("do afterThrowing  2222");
    }
}
