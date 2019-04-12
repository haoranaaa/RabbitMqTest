package com.dhr.test;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author haoran.duan
 * @since 24 二月 2019
 */
public class UnsafeBeanFactory {

    private static Unsafe unsafe;

    public static Unsafe getUnsafe(){
        if(unsafe==null) {
            synchronized (UnsafeBeanFactory.class){
                if(unsafe==null){
                    try {
                        Constructor<Unsafe> declaredConstructor = Unsafe.class.getDeclaredConstructor();
                        declaredConstructor.setAccessible(true);
                        unsafe = declaredConstructor.newInstance();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return unsafe;
    }

    public static void main(String[] args) {
        Unsafe unsafe = UnsafeBeanFactory.getUnsafe();
        System.out.println(unsafe);
    }
}
