package com.dhr.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @author duanhaoran
 * @since 2019/4/12 11:43 AM
 */
public class MapTest {

    public static void main(String[] args) {
        List< Map<String,Integer>> list= Lists.newArrayList();
        Map<String,Integer> map= Maps.newHashMap();
        map.put("a",1);
        map.put("b",2);
        Map<String,Integer> map2= Maps.newHashMap();
        map2.put("a",1);
        map2.put("b",2);
        list.add(map);
        list.add(map2);
        list.forEach(i->{
            i.put("c",3);
        });
        System.out.println(list);
    }
}
