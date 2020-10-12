package com.dhr.dataStructer;

import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import java.util.List;
import java.util.Map;

/**
 * @author duanhaoran
 * @since 2019/12/5 7:52 PM
 */
public class RangeAccumulationMap<K extends Comparable,T> {

    private RangeMap<K, List<T>> rangeMap;

    public RangeAccumulationMap(Map<K, List<T>> datas) {
        init(datas);
    }

    private void init(Map<K, List<T>> datas) {
        rangeMap = TreeRangeMap.create();
    }

}
