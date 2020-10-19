package com.dhr.compute;

/**
 * 滑动窗口
 *
 * @author duanhaoran
 * @since 2020/6/21 1:03 AM
 */
public class SlidingWindow {

    public static void main(String[] args) {
        SlidingWindow slidingWindow = new SlidingWindow();
//        int i = slidingWindow.numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2);
//        System.out.println(i);
        slidingWindow.kmp(new int[]{123, 1, 4321, 4, 2, 2321
                , 3, 8, 32, 54, 12, 31, 3, 21, 5412, 3, 123, 12, 5, 12}, 3);

    }

    public void kmp(int[] nums, int k) {
        int[] p = new int[nums.length];
        int hh = 0, tt = -1;
        for (int i = 0; i < nums.length; i++) {
            if (hh <= tt && i - k + 1 > p[hh]) {
                hh++;
            }
            while (hh <= tt && nums[p[tt]] >= nums[i]) {
                tt--;
            }
            p[++tt] = i;
            if (i >= k - 1) {
                System.out.println(nums[p[hh]]);
            }
        }
    }

    /**
     * 输出含有k个奇数的 子数组数量
     * 滑动窗口
     */
    public int numberOfSubarrays2(int[] nums, int k) {
        return 0;
    }

    /**
     * 输出含有k个奇数的 子数组数量
     * 记录奇数位置实现
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int count = 0;
        int[] arr = new int[nums.length + 2];
        int l = 0;
        arr[0] = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                arr[++l] = i;
            }
        }
        arr[++l] = nums.length;
        for (int i = 1; i < arr.length - k - 2; i += 2) {
            count += (arr[i] - arr[i - 1]) * (arr[i + k] - arr[i + k - 1]);
        }
        return count;
    }
}
