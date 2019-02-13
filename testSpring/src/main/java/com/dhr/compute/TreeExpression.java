package com.dhr.compute;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 29 一月 2019
 */
public class TreeExpression {




    public String parseTernary(String expression) {
        // write your code here
        if(expression==null){
            return null;
        }
        char[] chars = expression.trim().toCharArray();
        StringBuffer sb=new StringBuffer();
        for (int i = chars.length-1; i >= 0; i--) {
            if(chars[i]=='?'){
                if(chars[i-1]=='T'){
                    chars[i]=chars[i+1];
                }else{
                    chars[i]=chars[i+3];
                }
            }
        }
        return String.valueOf(chars[0]);
    }

    public static void main(String[] args) {
        TreeExpression treeExpression=new TreeExpression();
        System.out.println(treeExpression.minMoves2(new int[]{1,0,0,8,6}));
        System.out.println(Arrays.toString(treeExpression.asteroidCollision(new int[] { 10, -5, 5, 1, -2, -14, 1 })));
    }

    public int minMoves2(int[] nums) {
        // Write your code here

        Arrays.sort(nums);
        int num1 = nums[nums.length / 2];
        int num=0;
        for(int k:nums){
            num+=Math.abs(num1-k);
        }
        return num;
    }

    public int[] asteroidCollision(int[] asteroids) {
        // write your code here
        Stack<Integer> stack=new Stack<>();
        stack.push(asteroids[0]);
        for(int i=1;i<asteroids.length;i++){
            int size = stack.size();
            for(int j = size;j>0;j--) {
                Integer peek = stack.peek();
                if ((peek > 0 && asteroids[i] < 0) || (peek < 0 && asteroids[i] > 0)) {
                    if (Math.abs(peek) > Math.abs(asteroids[i])) {
                        break;
                    } else if (Math.abs(peek) == Math.abs(asteroids[i])) {
                        stack.pop();
                        break;
                    }else if(stack.size()==0){
                        stack.push(asteroids[0]);
                        break;
                    }else{
                        stack.pop();
                        continue;
                    }
                } else {
                    stack.push(asteroids[i]);
                }
            }
        }
        int[] k=new int[stack.size()];
        for(int i=0;i<stack.size();i++){
            k[i]=stack.pop();
        }
        return k;
    }
}
