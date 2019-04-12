package com.dhr.compute;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 17 二月 2019
 */
public class IntegerMi {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(1024)+" ----- "+Integer.toBinaryString(1023));
        System.out.println(checkInt(1024));
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 1, 2, 3, 4);
        Map<Integer, Integer> collect = integers.stream().collect(Collectors.toMap(Function.identity(), Function.identity()));
        collect.forEach((i,j)-> System.out.println(i+":"+j));
    }

    private static boolean checkInt(int i){
        return (i & (i-1) ) ==0;
    }
}
