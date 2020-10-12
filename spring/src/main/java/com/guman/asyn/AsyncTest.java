package com.guman.asyn;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author duanhaoran
 * @since 2020/1/20 4:52 PM
 */
@Service
public class AsyncTest {


    @Async
    public void asynRun() {
        System.out.println(Thread.currentThread().getName() + "run ......");
    }

}
