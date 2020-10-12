package com.dhr.thread;

/**
 * @author duanhaoran
 * @since 2020/1/20 3:39 PM
 */
public class InhertableThreadLocalTest {

    private static ThreadLocal<String> threadLocal;

    private static InheritableThreadLocal<String> inheritableThreadLocal;

    public static void main(String[] args) {
        threadLocal = new ThreadLocal<>();
        threadLocal.set("a");
        inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set("b");
        new Thread(()->{
            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());
        }).start();


    }


}
