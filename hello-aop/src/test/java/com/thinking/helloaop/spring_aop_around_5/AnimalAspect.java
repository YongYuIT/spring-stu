package com.thinking.helloaop.spring_aop_around_5;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnimalAspect {

    @Pointcut("execution(* com.thinking.helloaop.spring_aop_around_5.Dog.sayName(..))")
    public void pointCut() {
        System.out.println("do pointCut T");
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("do before T");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("do after T");
    }

    @AfterReturning("pointCut()")
    public void afterReturing() {
        System.out.println("do afterReturing T");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("do afterThrowing T");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("before around");
        joinPoint.proceed(new Object[]{"change to aaa"});
        System.out.println("after around");
    }
}
