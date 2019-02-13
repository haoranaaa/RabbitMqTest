package com.dhr.test;

import java.util.Collection;
import java.util.Collections;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 01 一月 2019
 */
public class LinkIntCodeTest {
    public static void main(String[] args) {
       /* int[] result = getResult(new int[] { 2, 3, 4, 5, 6, 7, 8, 9 }, 11);
        System.out.println(result[0]+":"+result[1]);
        long start=System.currentTimeMillis();
        rotateString(new char[]{'a','b','c','d','e','f'},7);
        System.out.println(System.currentTimeMillis()-start);
        int[] a=new int[]{1,1,2,2,3,3,4,4,5,5,6,6};*/
        System.out.println(null == null);
        System.out.println(2138761735%10000);
        //mo();
        /*testshui(6);*/
    }


    /**
     * 给定一个字符串和一个偏移量，根据偏移量旋转字符串(从左向右旋转)
     *
     * 样例
     * 对于字符串 "abcdefg".
     *
     * offset=0 => "abcdefg"
     * offset=1 => "gabcdef"
     * offset=2 => "fgabcde"
     * offset=3 => "efgabcd"
     * 挑战
     * 在数组上原地旋转，使用O(1)的额外空间
     * @return
     */
    public static void rotateString(char[] str, int offset) {
        for (int i = 1; i <= offset % str.length; i++) {
            char end=str[str.length-1];
            for(int j=str.length-1;j>0;j--){
                str[j]=str[j-1];
            }
            str[0]=end;
        }
        for (char i:
             str) {
            System.out.println(i);
        }

    }
    private static int[] getResult(int[] numbers,int target){
        for (int i = 0; i < numbers.length; i++) {
            for(int j=i+1; j < numbers.length;j++){
                if(numbers[i]+numbers[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
    /**
     * 水仙花数的定义是，这个数等于他每一位上数的幂次之和 见维基百科的定义
     *
     * 比如一个3位的十进制整数153就是一个水仙花数。因为 153 = 13 + 53 + 33。
     *
     * 而一个4位的十进制数1634也是一个水仙花数，因为 1634 = 14 + 64 + 34 + 44。
     *
     * 给出n，找到所有的n位十进制水仙花数。
     * 比如 n = 1, 所有水仙花数为：[0,1,2,3,4,5,6,7,8,9]。
     * 而对于 n = 2, 则没有2位的水仙花数，返回 []。
     */
    private static void testshui(int n){
        for (int i = 3; i <= n; i++) {
            int k=100;
            while (String.valueOf(k).length()<=i){
                int count=0;
                String s = String.valueOf(k);
                for(int j=0;j<s.length();j++){
                    count+=Math.pow(Double.valueOf(s.split("")[j]),i);
                }
                if(count==k){
                    System.out.println(k);
                }
                k++;

            }
        }
    }
    public static void mo(){
        for (int i = 0; i < 100000; i++) {
            System.out.println(i%10000);
        }
    }
}
