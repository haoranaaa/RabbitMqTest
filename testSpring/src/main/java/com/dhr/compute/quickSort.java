package com.dhr.compute;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author haoran.duan
 * @since 27 一月 2019
 */
public class quickSort {

    public static void quiSort(int[] array, int start, int end) {
        if (start < end) {
            //递归遍历
            int position = partition(array, start, end);
            quiSort(array, start, position - 1);
            quiSort(array, position + 1, end);
        }
    }
    public static int partition(int[] array, int start, int end) {
        int position = start - 1;//开始时start前一个位置
        int base = array[end];//选择最后一个元素为基准
        for (int i = start; i < end; i++) {
            //从开始节点遍历
            if (array[i] <= base) {
                position++;
                //第i个元素和position位置元素交换位置
                int temp = array[position];
                array[position] = array[i];
                array[i] = temp;
            }
        }
        //最后一个元素与position交换位置
        int temp = array[position + 1];
        array[position + 1] = array[end];
        array[end] = temp;
        //返回基准所在的位置
        return position + 1;
    }


    public static void main(String[] args) {
        int[] array=new int[]{1,52,123,532,624,123,13,5432,6542,42,1341,2342,42341,315,151,5152};
        quick(array,0,array.length-1);
        String string = Arrays.toString(array);
        int i = binarySearch(array, 624, 0, array.length - 1);
        System.out.println(string);
        System.out.println(i);
    }


    public static void quickSort(int[] array,int start,int end){
        if(start<end){
            int partion=sort(array,start,end);
            quickSort(array,start,partion-1);
            quickSort(array,partion+1,end);
        }
    }

    private static int sort(int[] array, int start, int end) {
        int partion=start-1;
        int base=array[end];
        for(int i=start;i<end;i++){
            if(array[i]<=base){
                partion++;
                int res=array[i];
                array[i]=array[partion];
                array[partion]=res;
            }
        }
        int k=array[partion+1];
        array[partion+1]=array[end];
        array[end]=k;
        return partion+1;
    }


    public static int[] quick(int []array,int start,int end){
        if (start<end){
            int k=quickSort2(array,start,end);
            quickSort(array,start,k-1);
            quickSort(array,k+1,end);
        }
        return array;
    }

    private static int quickSort2(int[] array, int start, int end) {
        int partion=start-1;
        int base=array[end];
        for(int i=start;i<end;i++){
            if(array[i]<=base){
                partion++;
                int res=array[i];
                array[i]=array[partion];
                array[partion]=res;
            }
        }
        int res=array[partion+1];
        array[partion+1]=array[end];
        array[end]=res;
        return partion+1;
    }

    private static int binarySearch(int[] array, int n,int start,int end){
        while (start<end){
            int k=(end+start)/2;
            if(array[k]>n){
                end=k-1;
            }else if(array[k]<n){
                start=k+1;
            }else{
                return k;
            }
        }
        return -1;
    }

}
