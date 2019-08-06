package com.thinking.helloaop.spring_aop_multi_asprct_9;

import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnimalAspect3 implements Ordered {

    @Override
    public int getOrder() {
        return 1;
    }

    @Pointcut("execution(* com.thinking.helloaop.spring_aop_multi_asprct_9.Dog.sayName(..))")
    public void pointCut() {
        System.out.println("do pointCut  3333");
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("do before  3333");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("do after  3333");
    }

    @AfterReturning("pointCut()")
    public void afterReturing() {
        System.out.println("do afterReturing  3333");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("do afterThrowing  3333");
    }
}
