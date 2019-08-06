package com.thinking.helloaop.spring_aop_pointcut_4;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnimalAspect {

    @Pointcut("execution(* com.thinking.helloaop.spring_aop_pointcut_4.Dog.sayName(..))")
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
