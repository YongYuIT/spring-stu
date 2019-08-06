package com.thinking.helloaop.porxy_test;

public class Dog implements Animal {

    @Override
    public String sayName(String name) {
        String result = "Hello Im a dog, my name is " + name;
        System.out.println("doing sayName-->" + result);
        return result;
    }

    private String tips;

    @Override
    public void setTips(String t) {
        tips = t;
    }

    @Override
    public String getTips() {
        return tips;
    }
}
