package com.dhr.collection;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author duanhaoran
 * @since 2019/4/24 4:47 PM
 */
public class RangeMapTest {

    public static void main(String[] args) {
        RangeMap<Integer, Integer> map = TreeRangeMap.create();
        map.put(Range.openClosed(0,1),1);
        map.put(Range.closedOpen(1,2),2);
        map.put(Range.closedOpen(2,3),3);
        map.put(Range.closedOpen(3,4),4);
        map.put(Range.closedOpen(5,6),5);
        System.out.println(IntStream.range(0,6).boxed().map(map::get).collect(Collectors.toList()));
    }
}
