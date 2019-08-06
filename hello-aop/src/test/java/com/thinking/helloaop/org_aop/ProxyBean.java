package com.thinking.helloaop.org_aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBean implements InvocationHandler {

    private Object target;
    private Interceptor interceptor;

    public static Object getProxyBean(Object o, Interceptor i) {
        ProxyBean proxyBean = new ProxyBean();
        proxyBean.target = o;
        proxyBean.interceptor = i;
        Object proxy = Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), proxyBean);
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean exp = false;
        Invocation invocation = new Invocation(target, method, args);
        Object returnObj = null;
        try {
            if (this.interceptor.before()) {
                returnObj = this.interceptor.around(invocation);
            } else {
                returnObj = method.invoke(target, args);
            }
        } catch (Exception ex) {
            exp = true;
        }

        this.interceptor.after();
        if (exp) {
            this.interceptor.afterThrowing();
        } else {
            this.interceptor.afterReturing();
            return returnObj;
        }
        return null;
    }
}
