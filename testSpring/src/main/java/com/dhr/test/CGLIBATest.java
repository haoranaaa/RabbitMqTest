package com.dhr.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 * @author haoran.duan
 * @since 29 九月 2018
 */
public class CGLIBATest implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib代理！");

        return methodProxy.invokeSuper(o,objects);
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ATest.class);
        enhancer.setCallback(new CGLIBATest());
        enhancer.setClassLoader(new ClassLoader() {
        });
        ATest a  = (ATest) enhancer.create();
        a.Hello();
        System.out.println("----------------------------");

        ATest o=new ATest();
        o.Hello();

    }
}
