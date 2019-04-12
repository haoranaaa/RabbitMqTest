package com.dhr.compute;

import java.util.Arrays;

/**
 * @author duanhaoran
 * @since 2019/4/2 3:12 PM
 */
public class StreamMiddleNum {


    private int[] medianII(int[] nums){
        int[] middles=new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int[] ints = Arrays.copyOf(nums, i+1);
            middles[i]=getMiddle(ints);
        }
        return middles;
    }

    private int getMiddle(int[] nums){
        Arrays.sort(nums);
        return nums[(nums.length-1)/2];
    }

    public static void main(String[] args) {
        StreamMiddleNum streamMiddleNum=new StreamMiddleNum();
        int[] ints = streamMiddleNum.medianII(new int[]{4, 5, 1, 3, 2, 6, 0});
        System.out.println(Arrays.toString(ints));
    }

}
