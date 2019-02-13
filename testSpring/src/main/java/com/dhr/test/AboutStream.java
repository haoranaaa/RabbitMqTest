package com.dhr.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * @author haoran.duan
 * @since 29 十一月 2018
 */
public class AboutStream {

    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    private static final ExecutorService executor=new ThreadPoolExecutor(
            0,
            5,
            1000,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>()
            ,new ThreadFactoryBuilder().setNameFormat("hrThread-%s").build()
    );

    private static Integer localVerable=0;


    public static void main(String[] args) {
        AboutStream as=new AboutStream();
        as.m();
        System.out.println(as.a);
        System.out.println(as.s(as::getA));
        System.out.println(as.foo(i->"'"+i+"'"));
    }

    public void f(Consumer<String> p){
        p.accept("abc");
    }
    public <T> T s(Supplier<T> s){
        return s.get();
    }
    public String foo(Function<String,String> function){
        return function.apply("c");
    }
    public void m(){
        this.f(this::setA);
    }

}
