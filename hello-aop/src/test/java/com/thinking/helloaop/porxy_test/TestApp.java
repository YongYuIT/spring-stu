package com.thinking.helloaop.porxy_test;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestApp {
    @Test
    public void test() {
        Animal animal = new Dog();
        InvocationHandler handler = new MyProxy(animal);

        Animal pAnimal = (Animal) Proxy.newProxyInstance(handler.getClass().getClassLoader(), new Class[]{Animal.class}, handler);
        String reslut = pAnimal.sayName("fuck");
        System.out.println("get result-->" + reslut);
    }
}
