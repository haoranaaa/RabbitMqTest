package com.guman.test;

import com.guman.annotation.Wconfig;
import com.guman.asyn.AsyncTest;
import com.guman.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author duanhaoran
 * @since 2019/3/25 4:19 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class MainTest3 {

    @Resource
    private MainC mainC;

    @Resource
    private ABCTest abcTest;

    @Resource
    private AbcIm abcIm;

    @Resource
    private TestInterface aImpl;

    @Resource
    private TestInterface bImpl;

    @Resource
    private List<OrderService> orderServiceList;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static String a;

    static {
        a ="a";
    }

    @Test
    public void testOrder() {
        for (OrderService orderService : orderServiceList) {
            System.out.println(orderService.getOrder());
        }
    }

    @Test
    public void test(){
        List<Class<?>> allBeans = mainC.getAllBeans();
        allBeans.stream().map(Class::getSimpleName).forEach(System.out::println);
        String value = (String)AnnotationUtils.getDefaultValue(Wconfig.class, "value");
        System.out.println(value);

    }

    @Test
    public void testCursor() {
        for (int i = 0; i < 100; i++) {
            redisTemplate.opsForSet().add("a", "a"+i);
        }
        Cursor<String> b = redisTemplate.opsForSet().scan("a", ScanOptions.scanOptions().count(1).build());
        while (b.hasNext()) {
            String next = b.next();
            System.out.println(next);
        }



    }



    @Test
    public void test2(){
        abcTest.print();
    }
    @Test
    public void test4(){
        try {
            Thread.sleep(100*1500);
        } catch (InterruptedException e) {

        }
    }
    @Test
    public void test3() throws InterruptedException {
        System.out.println(Integer.MAX_VALUE > 604004586);
        System.out.println(Integer.valueOf("604004586"));
    }
    @Test
    public void test5() {
        System.out.println(bImpl.getStr());
    }

    public static String getA() {
        return a;
    }

    @Resource
    private AsyncTest asyncTest;

    @Test
    public void test6() {
        for (int i = 0; i < 100; i++) {
            asyncTest.asynRun();
        }
    }
}
