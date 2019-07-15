package com.dhr.executor;

import javafx.concurrent.Worker;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.concurrent.*;

/**
 * @author duanhaoran
 * @since 2019/4/1 2:10 PM
 */
public class ExecutorUtils {

    public static final ThreadPoolExecutor oneThreadPool=new ThreadPoolExecutor(
            1,1,1000,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(1000),new ThreadPoolExecutor.AbortPolicy());

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

    public static void main(String[] args) {
        ThreadPoolExecutor executor = getExecutor(1);
        for (int i = 0; i < 5; i++) {
            executor.execute(()-> {
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    System.out.println("线程沉睡出现异常～！");
                }
            });
        }

       /* executor.shutdown();
        System.out.println("关闭线程池！！！！");*/
        Class<?> clazz = executor.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == HashSet.class && field.getName().contains("workers")) {
                HashSet<Object> workers = (HashSet<Object>) ReflectionUtils.getField(field, executor);
                workers.clear();

            }
        }
        executor.submit(() -> System.out.println("我执行了！"));

    }
}
