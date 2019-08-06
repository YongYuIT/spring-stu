package com.thinking.helloaop.spring_aop_around_5;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal {
    @Override
    public String sayName(String name) {
        String result = "Hello Im a dog, my name is " + name;
        System.out.println("do sayName-->" + result);
        return result;
    }
}
