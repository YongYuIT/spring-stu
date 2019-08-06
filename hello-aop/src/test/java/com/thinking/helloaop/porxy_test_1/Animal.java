package com.thinking.helloaop.porxy_test_1;

public interface Animal {
    default void setTips(String tips) {
    }

    default String getTips() {
        return "";
    }

    String sayName(String name);
}
