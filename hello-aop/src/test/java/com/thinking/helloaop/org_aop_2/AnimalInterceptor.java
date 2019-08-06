package com.thinking.helloaop.org_aop_2;

import java.lang.reflect.InvocationTargetException;

public class AnimalInterceptor implements Interceptor {
    @Override
    public boolean before() {
        System.out.println("do before");
        return true;
    }

    @Override
    public void after() {
        System.out.println("do after");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("do around");
        Object obj = invocation.proceed();
        return obj;
    }

    @Override
    public void afterReturing() {
        System.out.println("do afterReturing");
    }

    @Override
    public void afterThrowing() {
        System.out.println("do afterThrowing");
    }

    @Override
    public boolean useAround() {
        return true;
    }
}
