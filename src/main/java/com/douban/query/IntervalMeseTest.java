package com.douban.query;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.guman.common.json.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author duanhaoran
 * @since 2020/6/12 4:32 PM
 */
public class IntervalMeseTest {
    public static void main(String[] args) {
        HashMap<String, int[][]> collect = Maps.newHashMap();
        int[][] A = {{1, 5}, {8, 12}, {15, 21}};
        int[][] B = {{3,6}, {7, 9}, {11, 13}};
        int[][] C = {{2,8}, {8, 21}};
        int[][] AR = reversArrays(A);
        int[][] B2 = intervalIntersection(AR, B);
        int[][] ABR = reversArrays(B2);
        int[][] C2 = intervalIntersection(ABR, C);
        System.out.println("A reuslt : " + JSON.writeValueAsString(A));
        System.out.println("B reuslt : " + JSON.writeValueAsString(B2));
        System.out.println("C reuslt : " + JSON.writeValueAsString(C2));
        List<int[][]> list = Lists.newArrayList();
        list.add(C);
        list.add(B);
        intervalIns(A, list, list.size()-1);
    }

    public static int[][] intervalIns(int[][] A, List<int[][]> B, int idx) {
        if (B.isEmpty()) {
            return A;
        }
        int[][] ints1 = intervalIntersection(reversArrays(A), B.remove(idx));
        System.out.println("result:" + JSON.writeValueAsString(ints1));
        return intervalIns(ints1, B, --idx);
    }

    public static int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
            // Let's check if A[i] intersects B[j].
            // lo - the startpoint of the intersection
            // hi - the endpoint of the intersection
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);
            if (lo <= hi)
                ans.add(new int[]{lo, hi});

            // Remove the interval with the smallest endpoint
            if (A[i][1] < B[j][1])
                i++;
            else
                j++;
        }

        return ans.toArray(new int[ans.size()][]);
    }
    public static int[][] reversArrays(int[][] A) {
        List<int[]> result = Lists.newArrayList();
        int i = 0;
        while( i + 1< A.length) {

            int begin = A[i][1];
            int end = A[i + 1][0];
            i++;
            result.add(new int[]{begin, end});
        }
        return result.toArray(new int[0][0]);
    }
}
