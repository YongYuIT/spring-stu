package com.thinking.helloaop.spring_aop_without_interface_8;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DogAspect {

    @Pointcut("execution(* com.thinking.helloaop.spring_aop_without_interface_8.Dog.sayName(..))")
    public void pointCut() {
        System.out.println("do pointCut U");
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("do before U");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("do after U");
    }

    @AfterReturning("pointCut()")
    public void afterReturing() {
        System.out.println("do afterReturing U");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("do afterThrowing U");
    }
}
