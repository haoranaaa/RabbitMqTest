package com.dhr.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 20 一月 2019
 */
public class LockTest {

    private static final TestLock lock = new TestLock();

    private static Integer k = 0;


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
               printLog();
            }).start();
        }

        new Thread(()->{

        }).start();
        new Thread(()->{

        }).start();
    }

    private static void printLog(){
        lock.lock();
        System.out.println(k);
        k++;
        lock.unlock();
    }
}
