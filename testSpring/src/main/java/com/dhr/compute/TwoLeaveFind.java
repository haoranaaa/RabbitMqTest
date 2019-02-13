package com.dhr.compute;

import com.google.common.base.Preconditions;

/**
 * 二分查找
 * @author haoran.duan
 * @since 27 一月 2019
 */
public class TwoLeaveFind {

    public static Integer binarySearch(int[] sortArray,int value){
        Preconditions.checkNotNull(sortArray);
        if(sortArray.length==0){
            return null;
        }
        int begin=0;
        int over=sortArray.length-1;
        while (begin<=over){
            int i = (over + begin) / 2;
            int middle=sortArray[i];
            if(middle>value){
                over=i-1;
            }else if(middle<value){
                begin=i+1;
            }else{
                return i;
            }
        }

        return null;
    }

    public static Integer binarySearch(int[] sortArray,int start,int last,int value){
        Preconditions.checkNotNull(sortArray);
        if(sortArray.length==0){
            return null;
        }
        if(value<sortArray[start] || value>sortArray[last] || start>last){
            return null;
        }else {
            int i = (start + last) / 2;
            if(value>sortArray[i]){
                binarySearch(sortArray,i+1,last,value);
            }else if(value<sortArray[i]){
                binarySearch(sortArray,start,i-1,value);
            }else {
                return i;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] sortArray = { 0, 1, 2, 3,4, 5, 6, 7, 8, 9 };
        Integer integer = binarySearch(sortArray, 4);
        System.out.println(integer);
        Integer integer2 = binarySearch(sortArray, 0,sortArray.length-1,4);
        System.out.println(integer2);
    }
}
