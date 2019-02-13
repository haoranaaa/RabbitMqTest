package com.dhr.test;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author haoran.duan
 * @since 10 十二月 2018
 */
public class SortTest {

    public static void main(String[] args) {
        SortTest test=new SortTest();
        List<String> strings = Lists.newArrayList("实验组", "对照组", "相对增幅","绝对增幅");
        test.sortList(strings);
        DateTime now = DateTime.now();
        DateTime dateTime = now.minusDays(30);//删除一个月前的数据
        System.out.println(dateTime);
        System.out.println(strings);

    }

    private void sortList(List<String> list){
        list.sort(Comparator.comparing(Function.identity()));

    }
}
