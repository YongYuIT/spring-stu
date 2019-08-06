package com.thinking.helloaop.spring_aop_declare_6;

public class SuperDog implements SuperAnimal {
    @Override
    public String doSuperFunc(String su) {
        String str = "this is super dog-->" + su;
        System.out.println(str);
        return str;
    }
}
