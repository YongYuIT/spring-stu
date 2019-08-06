package com.thinking.helloaop.org_aop_2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invocation {
    private Object[] params;
    private Method method;
    private Object target;

    public Invocation(Object o, Method m, Object[] p) {
        params = p;
        method = m;
        target = o;
    }

    public Object proceed() throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        return method.invoke(target, params);
    }
}
