package com.guman.asyn;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.alipay.remoting.NamedThreadFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author duanhaoran
 * @since 2020/1/20 4:45 PM
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ExecutorService executorService = Executors.newFixedThreadPool(2, new NamedThreadFactory("async-executor"));
        return TtlExecutors.getTtlExecutor(executorService);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
