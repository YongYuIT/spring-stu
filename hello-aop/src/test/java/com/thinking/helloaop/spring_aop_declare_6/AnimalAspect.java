package com.thinking.helloaop.spring_aop_declare_6;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnimalAspect {
    @DeclareParents(
            value = "com.thinking.helloaop.spring_aop_declare_6.Dog+",
            defaultImpl = SuperDog.class
    )
    public SuperAnimal superDog;
}
