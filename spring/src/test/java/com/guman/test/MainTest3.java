package com.guman.test;

import com.google.common.collect.Lists;
import com.guman.annotation.Wconfig;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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


    private static String a;

    static {
        a ="a";
    }



    @Test
    public void test(){
        List<Class<?>> allBeans = mainC.getAllBeans();
        allBeans.stream().map(Class::getSimpleName).forEach(System.out::println);
        String value = (String)AnnotationUtils.getDefaultValue(Wconfig.class, "value");
        System.out.println(value);

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
        System.out.println(aImpl.getStr());
        System.out.println(bImpl.getStr());
    }

    public static String getA() {
        return a;
    }

    public static void main(String[] args) {
        List<Integer> list = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
            Integer next = iterator.next();
            if (next < 5 ) {
                iterator.remove();
            }
        }
        System.out.println(list);
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.comparing(integer -> integer));

    }
}
