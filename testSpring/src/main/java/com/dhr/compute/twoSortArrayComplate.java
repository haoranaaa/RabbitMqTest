package com.dhr.compute;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class twoSortArrayComplate {
    /**
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        int len=A.length+B.length;
        int []res=new int[len];
        int a=0;int b=0;
        for(int i=0;i<len;i++){
            if( b>B.length-1 ||(a<A.length && A[a]<=B[b] )){
                res[i]=A[a];
                a++;
            }else{
                res[i]=B[b];
                b++;
            }
        }
        return res;
    }

    public static void main(String[] args) throws InterruptedException {
        twoSortArrayComplate complate=new twoSortArrayComplate();
        int[] ints = complate.mergeSortedArray(new int[]{1,5}, new int[]{2,3});
        System.out.println(Arrays.toString(ints));
        long timeMillis = System.currentTimeMillis();
        Thread.sleep(5000);
        System.out.println(System.currentTimeMillis()-timeMillis);
    }
}
