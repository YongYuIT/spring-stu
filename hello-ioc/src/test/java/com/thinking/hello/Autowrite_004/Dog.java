package com.thinking.hello.Autowrite_004;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal {
    @Override
    public void doJob() {
        System.out.println("我会看家");
    }
}
