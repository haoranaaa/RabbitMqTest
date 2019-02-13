package com.dhr.test;

import com.dhr.model.CacheModel;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.joda.time.DateTime;

import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author haoran.duan
 * @since 09 一月 2019
 */
public class CacheTest {

    private Cache<String,String> cache=CacheBuilder.newBuilder()
            .maximumSize(10)
            .expireAfterAccess(1000,TimeUnit.MILLISECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return new DateTime().minuteOfHour()+key;
                }
            });
    private static final ListeningExecutorService executor=MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));


    public static void main(String[] args) {
        CacheTest cacheTest=new CacheTest();
        cacheTest.cache.getIfPresent("sss");
    }
    class CacheListen implements Runnable {

        private Queue<CacheModel> queue;

        public CacheListen(Queue queue){
            this.queue=queue;
        }

        @Override
        public void run() {
            for(;;){

            }
        }
    }
}
