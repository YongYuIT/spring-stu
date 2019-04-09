package com.thinking.hello.Autowrite_004;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class House {

    @Autowired()
    @Qualifier("dog")
    private Animal animal;

    @Autowired
    private Dog dog;

    public void doSomeThing(){
        animal.doJob();
        dog.doJob();
    }
}
