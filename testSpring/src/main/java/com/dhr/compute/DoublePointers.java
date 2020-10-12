package com.dhr.compute;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author duanhaoran
 * @since 2020/9/26 4:40 PM
 */
public class DoublePointers {

    /**
     * 给定一个长度为n的整数序列，请找出最长的不包含重复的数的连续区间，输出它的长度。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(longestIntegerArraySize(new int[]{1, 2, 2, 3, 5}));
    }

    private static int longestIntegerArraySize(int[] nums) {
        Map<Integer, Integer> map = Maps.newHashMap();
        int max = 0;
        for (int i = 0, j = 0; i < nums.length; i++) {
            map.compute(nums[i], (k, o)-> o == null ? 1 : ++o);
            while (map.get(nums[i]) > 1){
                map.computeIfPresent(nums[j], (k,o)-> --o);
                j++;
            }
            max = Math.max(i - j + 1, max);
        }
        return max;
    }


    /**
     * 获取一个数字二进制的第l位是多少
     *
     * @param k 数字
     * @param l 位置
     * @return
     */
    private static int getIntegerPositionNum(int k, int l) {
        int i = k >> l & 1;
        return i;
    }
}
