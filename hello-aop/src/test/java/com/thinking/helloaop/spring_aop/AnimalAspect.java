package com.thinking.helloaop.spring_aop;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnimalAspect {
    @Before("execution(* com.thinking.helloaop.spring_aop.Dog.sayName(..))")
    public void before() {
        System.out.println("do before");
    }

    @After("execution(* com.thinking.helloaop.spring_aop.Dog.sayName(..))")
    public void after() {
        System.out.println("do after");
    }

    @AfterReturning("execution(* com.thinking.helloaop.spring_aop.Dog.sayName(..))")
    public void afterReturing() {
        System.out.println("do afterReturing");
    }

    @AfterThrowing("execution(* com.thinking.helloaop.spring_aop.Dog.sayName(..))")
    public void afterThrowing() {
        System.out.println("do afterThrowing");
    }
}
