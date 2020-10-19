package com.dhr.compute;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 区间合并
 * @author duanhaoran
 * @since 2020/10/14 10:47 AM
 */
public class MergeDoubleArray {




    public static int merge(ArrayList<int[]> list){
        ArrayList<int[]> res = new ArrayList<>();
        list.sort(Comparator.comparingInt(o -> o[0]));
        int l = Integer.MIN_VALUE;
        int r = Integer.MIN_VALUE;
        for (int[] a : list) {
            //下一个区间左端点大于老区间右端点
            if (a[0] > r){
                //找到了新区间，就将老区间添加(不直接加新区间，因为新区间右端点还没确定)
                if (l != Integer.MIN_VALUE){
                    res.add(new int[]{l,r});
                }
                l = a[0];
                r = a[1];
            }else {
                r = Math.max(r, a[1]);
            }
        }
        //最后一个合并区间,判断条件防止list为空
        if (l != Integer.MIN_VALUE){
            res.add(new int[]{l,r});
        }
        return res.size();
    }

}
