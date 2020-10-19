package com.dhr.compute;

import com.dhr.bean.Team;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 给定一个长度为n的整数数列，以及一个整数k，请用快速选择算法求出数列从小到大排序后的第k个数。
 *
 * @author duanhaoran
 * @since 2020/10/15 5:03 PM
 */
public class TheNMinNum {


    public static void main(String[] args) {
//        int[] nums = new int[]{6,5,23,42,62,36,324,23,4,236,23,523,4,234};
//        nMinNum(nums, 5);

        Team team = null;

        Object[] objects = Optional.ofNullable(team).map(Team::getCamps).map(List::toArray).orElse(null);
        System.out.println(objects);
    }

    private static int nMinNum(int[] nums, int i) {
        if (nums.length < i) {
            return -1;
        }
        sortPart(nums, 0, nums.length-1);
        System.out.println(Arrays.toString(nums));
        return 0;
    }

    private static void sortPart(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        int k =start;
        int s = start - 1, e = end + 1;
        while (s < e) {
            while (nums[++s] < nums[k]) {
            }
            while (nums[--e] > nums[k]) {
            }
            if (s < e) {
                int r = nums[s];
                nums[s] = nums[e];
                nums[e] = r;
            }
        }
        sortPart(nums, start, e);
        sortPart(nums, e + 1, end);
    }
}
