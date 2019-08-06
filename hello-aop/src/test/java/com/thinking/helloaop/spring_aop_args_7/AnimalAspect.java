package com.thinking.helloaop.spring_aop_args_7;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnimalAspect {

    @Pointcut("execution(* com.thinking.helloaop.spring_aop_args_7.Dog.sayName(..))")
    public void pointCut() {
        System.out.println("do pointCut U");
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("do before U");

    }

    @After("pointCut() && args(dog_name)")
    public void after(JoinPoint jp, String dog_name) {
        System.out.println("after########name-->" + dog_name);
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
