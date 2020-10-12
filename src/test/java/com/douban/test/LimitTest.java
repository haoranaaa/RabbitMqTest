package com.douban.test;

import com.alipay.remoting.NamedThreadFactory;
import com.google.common.util.concurrent.RateLimiter;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author duanhaoran
 * @since 2020/4/28 2:21 PM
 */
public class LimitTest {

    private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    /**
     * 令牌池限流
     * 主要对流量进行控制
     * 动态调整 速率:
     * @see RateLimiter#setRate(double)
     */
    @Test
    public void tokenPoolTest() throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(100);
        executor.submit(() -> System.out.println(rateLimiter.tryAcquire(10000)));
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                int pushMessageSize = getPushMessageSize();
                if (rateLimiter.tryAcquire(pushMessageSize)) {
                    //push message
                    sendMessage(10);
                    System.out.println("send message success ! " + pushMessageSize);
                    //todo 控制 动态阈值调整
                } else {
                    System.out.println("do not get "+ pushMessageSize + " token!");
                }
                System.out.println();
            });
        }
    }

    /**
     * 信号量限流
     * 漏桶
     * 主要为了控制并发流量太大
     */
    @Test
    public void semaphoreTest() {
        Semaphore semaphore = new Semaphore(100);
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                int messageSize = getPushMessageSize();
                if (semaphore.tryAcquire(messageSize)) {
                    try {
                        //push message
                        sendMessage(10);
                    } finally {
                        semaphore.release(messageSize);
                    }
                } else {
                    System.out.println("do not get token!");
                }
            });
        }
    }

    /**
     * 队列限流
     * 漏桶
     * runnable是指一条群聊消息的下发 1->more
     */
    @Test
    public void one2MoreQueueLimitTest() {
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        for (int i = 0; i < 100; i++) {
            try {
                queue.add(() -> {
                    //doSomeThing
                    sendMessage(100);
                });
            } catch (IllegalStateException e) {
                System.out.println("queue is full , add fail !");
            }
        }
    }

    /**
     * 队列限流
     * runnable是指一条群聊消息的下发 1->1
     */
    @Test
    public void one2OneQueueLimitTest() {
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
        for (int i = 0; i < 10; i++) {
            try {
                queue.add(() -> {
                    //doSomeThing
                    sendMessage(20);
                });
            } catch (IllegalStateException e) {
                System.out.println("queue is full , add fail !");
            }
        }
    }

    /**
     * 计数器限流
     * 漏桶
     */
    @Test
    public void numberLimitTest() {
        NumberLimiter numberLimiter = new NumberLimiter(10);
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                if (numberLimiter.tryAcquire(getPushMessageSize())) {
                    //push message
                    sendMessage(10);
                } else {
                    System.out.println("do not get token!");
                }
            });
        }
    }

    private static final String REDIS_LIMIT_KEY = "redis-limit:%s";

    /**
     * 分布式流量控制 - redis
     */
    @Test
    public void redisLimitTest() {
        String format = String.format(REDIS_LIMIT_KEY, DateTime.now().getMinuteOfDay());
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
                try {
                    String incr = jedisPool.getResource().get(format);
                    long count = 0L;
                    if (incr != null) {
                        count = Long.valueOf(incr);
                    }
                    int pushMessageSize = getPushMessageSize();
                    if (count + pushMessageSize <= 100) {
                        Long aLong = jedisPool.getResource().incrBy(format, pushMessageSize);
                        if (aLong == pushMessageSize) {
                            jedisPool.getResource().expire(format, 60);
                        }
                        //push message
                        sendMessage(10);
                    } else {
                        System.out.println("do not get token!");
                    }
                } catch (Throwable e) {
                    System.out.println(e.getMessage());
                }finally {
                    jedisPool.close();
                }
            });
        }

    }

    /**
     * get should send to channel size
     */
    private int getPushMessageSize() {
        return ThreadLocalRandom.current().nextInt(3, 50);
    }

    /**
     * model like send message
     * @param millis
     */
    private void sendMessage(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            //ignore
        }
    }

    public class NumberLimiter{

        private final LongAdder ZERO = new LongAdder();

        private LongAdder counter = new LongAdder();

        private long timestamp = System.currentTimeMillis();

        private long permitsPerSecond;

        public NumberLimiter(long permitsPerSecond) {
            this.permitsPerSecond = permitsPerSecond;
        }

        public boolean tryAcquire(int permits) {
            long now = System.currentTimeMillis();
            if (now - timestamp < 1000) {
                if (counter.intValue() + permits < permitsPerSecond) {
                    counter.add(permits);
                    return true;
                } else {
                    return false;
                }
            } else {
                counter = ZERO;
                timestamp = now;
                return false;
            }
        }

    }

    public class QueueThreadPoolExecutorFactory extends NamedThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            return super.newThread(r);
        }
    }

}
