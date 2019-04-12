package com.dhr.compute;

public class BinarySearch {
    public int binarySearch(int[] nums, int target) {
        // write your code here
        if(nums.length==0 || target<nums[0] || target>nums[nums.length-1]){
            return -1;
        }
        int start=0;
        int end=nums.length-1;
        while(start<=end){
            int mid=(start+end)/2 ;
            if(nums[mid]>target){
                end=mid-1;
            }else if(nums[mid]<target){
                start=mid+1;
            }else{
                while(mid>0 && nums[mid]==nums[mid-1]){
                    mid--;
                }
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch search=new BinarySearch();
        int i = search.binarySearch(new int[]{4,5,9,9,12,13,14,15,15,18}, 10);
        System.out.println(i);
    }

}
