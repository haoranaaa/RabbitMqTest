package com.dhr.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 29 九月 2018
 */
public class MainTest {
    public static void main(String[] args) {
        try {
            proxy();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static void proxy() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ProxyTestImpl proxyTest=new ProxyTestImpl();
        Test o = (Test) Proxy.newProxyInstance(TestImpl.class.getClassLoader(),TestImpl.class.getInterfaces(), new ProxyTestImpl(new TestImpl()));
        o.test1();

        Class<?> testImpl = Class.forName("com.dhr.test.TestImpl2");

        Method[] methods = testImpl.getMethods();
        TestImpl2 test1 = (TestImpl2) testImpl.newInstance();

        for(Method method:methods){
            method.setAccessible(true);
            Object invoke = method.invoke(test1, null);
        }
    }
}
