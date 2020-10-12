package com.dhr.compute;


/**
 * @author haoran.duan
 * @since 27 一月 2019
 */
public class Sort {

    public static void main(String[] args) {
        int[] array = new int[]{1, 52, 123, 532, 624, 123, 13, 5432, 6542, 42, 1341, 2342, 42341, 315, 151, 5152};
        quickSort2(array, 0, array.length - 1);
//        mergeSort(array,0,array.length-1);
        for (int i : array) {
            System.out.println(i);
        }
    }

    public static void quickSort2(int[] nums, int s, int e) {
        if (s > e) {
            return;
        }
        int n = s, i = s-1, j = e+1;
        while (i < j) {
            while (nums[++i] < nums[n]) {
            }
            while (nums[--j] > nums[n]) {
            }
            if (i < j) {
                int a = nums[i];
                nums[i] = nums[j];
                nums[j] = a;
            }
        }
        quickSort(nums, s, j);
        quickSort(nums, j+1, e);

    }


    private static void mergeSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) >> 1;
        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);
        int i = start, j = mid + 1;
        int k =0;
        int[] tmp = new int[array.length];
        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = array[i++];
        }
        while (j <= end) {
            tmp[k++] = array[j++];
        }
        k = 0;
        while (start <= end) {
            array[start++] = tmp[k++];
        }

    }

    private static void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = start - 1, j = end + 1;
        while (i < j) {
            while (array[++i] < array[start]){}
            while (array[--j] > array[start]) {
            }
            if (i < j) {
                swap(array, i, j);
            }
        }
        quickSort(array, start, j);
        quickSort(array, j+1, end);
    }

    private static void swap(int[] array, int i, int j) {
        int l = array[i];
        array[i] = array[j];
        array[j] = l;

    }


}
