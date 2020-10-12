package com.dhr.executor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description disruptor代码样例。每10ms向disruptor中插入一个元素，消费者读取数据，并打印到终端
 * @author duanhaoran
 * @since 2020/5/15 7:22 PM
 */


public class DisruptorMain
{
    public static void main(String[] args) throws Exception
    {
        // 队列中的元素
        class Element {

            private int value;

            public int get(){
                return value;
            }

            public void set(int value){
                this.value= value;
            }

        }

        // 生产者的线程工厂
        ThreadFactory threadFactory = r -> new Thread(r, "simpleThread");

        // RingBuffer生产工厂,初始化RingBuffer的时候使用
        EventFactory<Element> factory = Element::new;

        // 处理Event的handler
        EventHandler<Element> handler = (element, sequence, endOfBatch) -> {
            System.out.println("Element: " + element.get());
            Thread.sleep(100);
        };

        // 阻塞策略
        TimeoutBlockingWaitStrategy strategy = new TimeoutBlockingWaitStrategy(1, TimeUnit.MILLISECONDS);

        // 指定RingBuffer的大小
        int bufferSize = 32;

        // 创建disruptor，采用单生产者模式
        Disruptor<Element> disruptor = new Disruptor<>(factory, bufferSize, threadFactory, ProducerType.MULTI, strategy);

        // 设置EventHandler
        disruptor.handleEventsWith(handler);

        // 启动disruptor的线程
        disruptor.start();

        RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();

        new Thread(() -> {
            try {
                for (int l = 0; true; l++) {
                    // 获取下一个可用位置的下标
                    long sequence = ringBuffer.next();
                    try {
                        // 返回可用位置的元素
                        Element event = ringBuffer.get(sequence);
                        // 设置该位置元素的值
                        event.set(l);
                    } finally {
                        ringBuffer.publish(sequence);
                    }
//            Thread.sleep(10);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }).start();

        new Thread(() -> {
            for (int l = 0; true; l++)
            {
                // 获取下一个可用位置的下标
                long sequence = ringBuffer.next();
                try
                {
                    // 返回可用位置的元素
                    Element event = ringBuffer.get(sequence);
                    // 设置该位置元素的值
                    event.set(l);
                }
                finally
                {
                    ringBuffer.publish(sequence);
                }
//            Thread.sleep(10);
            }
        }).start();

        Thread.sleep(1000 * 60);

    }
}
