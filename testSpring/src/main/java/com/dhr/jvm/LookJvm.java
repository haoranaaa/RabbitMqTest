package com.dhr.jvm;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 11 二月 2019
 */
public class LookJvm {
    private static final double ONE_MB = 1024 * 1024 * 1.0;

    private String A;

    private Integer B;

    public LookJvm(String string,Integer b){
        A=string;
        B=b;
    }

    public static void main(String[] args) {
        List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
        memoryPoolMXBeans.forEach(i -> {
            System.out.println("name:" + i.getName());
            System.out.println("type:" + i.getType());
            System.out.println("usage/used:" + i.getUsage().getUsed() / ONE_MB + "MB");
        });
        try {
            LookJvm lookJvm = LookJvm.class.getConstructor(String.class,Integer.class).newInstance("b",10);
            lookJvm.print();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.out.println("初始化参数异常！");
        }
        ListeningExecutorService executor=MoreExecutors.listeningDecorator(
                new ThreadPoolExecutor(1,2,10,TimeUnit.SECONDS,new ArrayBlockingQueue<>(10),new ThreadPoolExecutor.AbortPolicy()));
        ListenableFuture<String> a = executor.submit(() -> {
            return "a";
        });
        a.addListener(()->{

        },executor);

    }

    private void print(){
        System.out.println(A+":"+B);
    }
    interface a{
        static void a(){

        }
        short xx();

        String a="sadad";
    }
}
