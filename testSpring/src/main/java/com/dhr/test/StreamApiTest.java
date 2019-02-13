package com.dhr.test;

import com.google.common.cache.Cache;

import java.util.function.Consumer;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 13 十二月 2018
 */
public class StreamApiTest {

    public static void main(String[] args) {
        StreamApiTest test=new StreamApiTest();
        test.test1();
    }

    private void test1(){
        Integer i=10;
        Integer finalI = i;
        Consumer<Integer> consumer=(j)-> System.out.println(finalI);
        i++;
        consumer.accept(1);
    }
}
