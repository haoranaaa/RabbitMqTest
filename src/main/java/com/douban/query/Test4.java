package com.douban.query;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;

/**
 * @author duanhaoran
 * @since 2020/4/8 1:55 PM
 */
public class Test4 {

    public ExecutorService executorService;

    public static RateLimiter rateLimiter = RateLimiter.create(1000);

    public static void main(String[] args) {

    }
    @SuppressWarnings("unchecked")
    public static void consumerToken() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    if (!rateLimiter.tryAcquire(1)) {
                        System.out.println("get token fail !");
                    } else {
                        rateLimiter.acquire(1);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        //ignore
                    }
                }
            }).start();
        }
    }
}
