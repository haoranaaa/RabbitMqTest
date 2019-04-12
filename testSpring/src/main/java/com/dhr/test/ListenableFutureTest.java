package com.dhr.test;

import com.dhr.executor.ExecutorUtils;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ExecutorService;

/**
 * @author duanhaoran
 * @since 2019/4/1 2:08 PM
 */
public class ListenableFutureTest {


    public static void main(String[] args) {
        ListeningExecutorService executor = MoreExecutors.listeningDecorator(ExecutorUtils.getExecutor(1));
        ListenableFuture<?> submit = executor.submit(() -> {
            try {
                Thread.sleep(2000);
                return;
            } catch (InterruptedException e) {
                // ignore
            }
        });
        System.out.println(submit.isDone());
        submit.addListener(()->{
            System.out.println("等待完成");
        },executor);
    }
}
