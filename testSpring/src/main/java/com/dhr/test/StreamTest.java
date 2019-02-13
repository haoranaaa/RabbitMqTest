package com.dhr.test;

import java.util.stream.IntStream;

/**
 *
 * @author haoran.duan
 * @since 28 十一月 2018
 */
public class StreamTest {

    public static void main(String[] args) {
        IntStream.range(0,10).boxed().map(i->i+=1).forEach(System.out::print);
    }
}
