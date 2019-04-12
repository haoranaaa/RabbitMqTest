package com.dhr.test;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class CatchFinallyTest {

    static volatile List<Integer> list=new ArrayList<>();

    public static void main(String[] args) {
        int test = test();
        System.out.println(test);
        new Son();
        int[] nums=new int[]{1,-1,-2,-3,2,3,4,5,6};
        ArrayList<Integer> objects = Lists.newArrayList();
        for(int num:nums){
            objects.add(num);
        }
        Integer integer = objects.stream().filter(i -> i > 0).limit(5).reduce((x, y) -> x + y).orElse(0);
        System.out.println(integer);
        for (int i = 0; i < 5; i++) {
            list.add(5-i);
        }
        for(Integer num:list){
            if(num%2==0){
                list.remove(num);
            }
        }
        System.out.println(list);
    }

    public static int test(){
        int i=0;
        try {
            i++;
            return i++/0;
        }catch (Exception e){
            i++;
            return i++;
        }finally {
            i++;
            return ++i;
        }

    }
}
class Parent{
    static {
        System.out.println("1,");
    }
    public Parent(){
        System.out.println("2,");
    }
}
class Son extends Parent{
    static {
        System.out.println("3,");
    }
    public Son(){
        super();
        System.out.println("4,");
    }
}
