package com.dhr.test;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author haoran.duan
 * @since 01 二月 2019
 */
public class test3 {
    public static void main(String[] args) {
        double a=0x0.0000000000001P-1022;
        test3 test3=new test3();

        System.out.println(test3.KSubstring("abcabc",3));
        ArrayList<Object> objects = Lists.newArrayList();
        Stack<Integer> stack=new Stack<Integer>();
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
        }
    }


    public int KSubstring(String stringIn, int K) {
        // Write your code here
        char[] strs=stringIn.toCharArray();
        Set<String> set=new HashSet();
        for(int i=0;i<strs.length-K+1;i++){
            String a=new String();
            for(int j=0;j<K;j++){
                a+=strs[i+j];
            }
            if(!set.contains(a)){
                set.add(a);
            }
        }
        return set.size();
    }

}
