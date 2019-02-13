package com.dhr.queue;

import com.google.common.base.Preconditions;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author haoran.duan
 * @since 11 二月 2019
 */
public class Consumer<T> {

    private BlockingQueue<T> queue;

    public Consumer(Provider<T> provider){
        Preconditions.checkNotNull(provider);
        provider.register(this);
    }

    public void setQueue(BlockingQueue<T> queue) {
        Preconditions.checkNotNull(queue);
        this.queue = queue;
    }

    public void consumption(java.util.function.Consumer<T> compute){
        Preconditions.checkNotNull(queue);
        Preconditions.checkNotNull(compute);
        try {
            T data = queue.take();
            compute.accept(data);
        } catch (InterruptedException e) {
            throw new IllegalArgumentException("this thread can not be interrupter !");
        }
    }

    public T get(){
        try {
            Preconditions.checkNotNull(queue);
            return queue.take();
        } catch (InterruptedException e) {
            throw new IllegalArgumentException("this thread can not be interrupter !");
        }
    }
}
