package com.dhr.jvm;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author duanhaoran
 * @since 2019/4/3 4:58 PM
 */
public class RuntimeTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
            return  "a";
        });
        Thread.sleep(2000);
        future.whenComplete((i,j)-> System.out.println(i+":"+j));
    }
}
