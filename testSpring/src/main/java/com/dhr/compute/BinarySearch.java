package com.dhr.compute;

public class BinarySearch {

    /**
     * 获取一个数值 在有序数组中的的开始和结束  位置
     *
     * @param nums
     * @param target
     * @return
     */
    public void binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (nums[l] != target) {
            System.out.println(-1);
        }
        System.out.println(l);
        l = 0;
        r = nums.length - 1;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(l);
    }

    public static void main(String[] args) {
        BinarySearch search = new BinarySearch();
        int[] nums = {1, 2, 3, 4, 9, 9, 9, 9, 12, 13, 14, 15, 15, 18};
        search.binarySearch(nums, 9);
    }


}
