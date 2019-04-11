package com.thinking.helloaop.porxy_test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxy implements InvocationHandler {

    private Object object;

    public MyProxy(Object _object) {
        object = _object;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] prarms) throws Throwable {
        System.out.println("before doing " + method.getName());
        Object reuslt = method.invoke(object, prarms);
        System.out.println("after doing " + method.getName());
        return reuslt;
    }
}
