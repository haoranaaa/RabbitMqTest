package com.dhr.executor;

import java.util.concurrent.*;

/**
 * @author duanhaoran
 * @since 2019/4/1 2:10 PM
 */
public class ExecutorUtils {

    public static final ThreadPoolExecutor oneThreadPool=new ThreadPoolExecutor(
            1,1,1000,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(1),new ThreadPoolExecutor.AbortPolicy());

    public static final ThreadPoolExecutor normalThreadPool=new ThreadPoolExecutor(
            5,10,100,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(10));

    public static ThreadPoolExecutor getExecutor(int size){
        switch (size){
            case 1:
                return oneThreadPool;
            default:
                return normalThreadPool;
        }
    }
}
