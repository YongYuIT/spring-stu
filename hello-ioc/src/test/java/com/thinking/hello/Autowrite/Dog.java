package com.thinking.hello.Autowrite;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal {
    @Override
    public void doJob() {
        System.out.println("我会看家");
    }
}
