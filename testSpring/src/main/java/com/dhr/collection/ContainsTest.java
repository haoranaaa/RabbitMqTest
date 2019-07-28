package com.dhr.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author duanhaoran
 * @since 2019/7/25 7:51 PM
 */
public class ContainsTest {

    public static void main(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList();
        HashSet<Integer> set = Sets.newHashSet();

        list.add(2);
        set.add(null);
        System.out.println(Runtime.getRuntime().availableProcessors());
        set.add(1);
        System.out.println(set.containsAll(list));
        System.out.println(set);
        System.out.println(list);
    }
}
