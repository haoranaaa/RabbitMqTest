package com.dhr.compute;

/**
 * @author duanhaoran
 */
public class SameNums {

    public static void main(String[] args) {
        int[] nums=new int[]{1,1,2,2,3,3,4,4,5};
        int k=nums[0];
        for (int i = 1; i < nums.length; i++) {
            k=k ^ nums[i];
            System.out.println(k);
        }
    }
}
