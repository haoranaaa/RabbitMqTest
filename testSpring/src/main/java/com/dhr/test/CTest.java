package com.dhr.test;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 08 十月 2018
 */
public class CTest {
    public static void main(String[] args) {
        ArrayList<String> objects = Lists.newArrayList();
        objects.add("a");objects.add("b");

        objects.stream().filter(i->i.equals("a")).forEach(System.out::print);
    }
}
