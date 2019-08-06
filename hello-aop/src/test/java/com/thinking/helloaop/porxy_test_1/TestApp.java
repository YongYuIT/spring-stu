package com.thinking.helloaop.porxy_test_1;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestApp {
    @Test
    public void test() {
        Animal animal = new Dog();
        animal.setTips("aaaa");
        InvocationHandler handler = new MyProxy(animal);

        Animal pAnimal = (Animal) Proxy.newProxyInstance(handler.getClass().getClassLoader(), new Class[]{Animal.class}, handler);
        //代理对象的属性值也会从原对象拷贝过来，如果没有此步赋值，pAnimal的tips仍是aaaa
        pAnimal.setTips("bbbb");
        String reslut = pAnimal.sayName("fuck");
        System.out.println("get result-->" + reslut);
    }
}
