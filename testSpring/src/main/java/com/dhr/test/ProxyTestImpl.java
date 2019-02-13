package com.dhr.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 *
 * @author haoran.duan
 * @since 29 九月 2018
 */
public class ProxyTestImpl implements InvocationHandler {
    public ProxyTestImpl(){

    }

    public ProxyTestImpl(Object obj){
        this.proxyClass=obj;
    }

    private Object proxyClass;


    public void test(){
        System.out.println("我是代理类，我想被增强！");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("增强方法！！！！----------");
        Object invoke = method.invoke(proxyClass, args);

        System.out.println("增强完成！！！------");

        return invoke;
    }
}
