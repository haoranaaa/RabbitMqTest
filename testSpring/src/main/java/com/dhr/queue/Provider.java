package com.dhr.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 *
 * @author haoran.duan
 * @since 11 二月 2019
 */
public class Provider<T>{

    private static ExecutorService executor = new ThreadPoolExecutor(
            ThreadPoolConstant.CORE_SIZE,
            ThreadPoolConstant.MAX_POOL_SIZE,
            1000,TimeUnit.MILLISECONDS
            ,new LinkedBlockingQueue<>(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    private BlockingQueue<T> queue;

    private static AtomicInteger integer=new AtomicInteger();

    public Provider(BlockingQueue<T> blockingQueue){
        this.queue=blockingQueue;
    }

    public boolean push(T data){
        boolean offer = this.queue.offer(data);
        integer.getAndIncrement();
        return offer;
    }

    public boolean push(Supplier<T> supplier){
        boolean offer = this.queue.offer(supplier.get());
        integer.getAndIncrement();
        return offer;
    }

    public void register(Consumer<T> consumer){
        consumer.setQueue(this.queue);
    }

    public static void main(String[] args) {
        System.out.println(10000000>>3);
    }

}
