package com.thinking.helloaop.spring_aop_multi_asprct_9;

import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnimalAspect1 implements Ordered {

    @Override
    public int getOrder() {
        return 2;
    }

    @Pointcut("execution(* com.thinking.helloaop.spring_aop_multi_asprct_9.Dog.sayName(..))")
    public void pointCut() {
        System.out.println("do pointCut  1111");
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("do before  1111");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("do after  1111");
    }

    @AfterReturning("pointCut()")
    public void afterReturing() {
        System.out.println("do afterReturing  1111");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("do afterThrowing  1111");
    }
}
