package com.guman.test;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author duanhaoran
 * @since 2019/4/13 1:27 AM
 */
public abstract class AbstractService {

    public static void main(String[] args) {
        List<Integer> list =Lists.newLinkedList();
        list.add(0);
        list.add(1);
        list.add(0,5);
        System.out.println(list);
    }
}
