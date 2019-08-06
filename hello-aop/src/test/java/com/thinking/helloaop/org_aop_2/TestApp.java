package com.thinking.helloaop.org_aop_2;

import org.junit.Test;

public class TestApp {
    @Test
    public void test() {
        Animal dog = new Dog();
        Animal pDog = (Animal) ProxyBean.getProxyBean(dog, new AnimalInterceptor());
        pDog.sayName("fuckU");
    }
}
