package com.thinking.helloaop.org_aop_2;

import java.lang.reflect.InvocationTargetException;

public interface Interceptor {
    boolean before();

    void after();

    Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

    void afterReturing();

    void afterThrowing();

    boolean useAround();
}
