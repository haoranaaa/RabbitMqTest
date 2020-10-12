package com.douban.query.model;

import com.guman.common.json.JSON;

/**
 * @author duanhaoran
 * @since 2020/4/7 8:39 PM
 */
public class TestMartix {


    public static void main(String[] args) {
        double[] doubles = {
                4,6,6,5.7,6,8
        };
        int[] ints = new int[doubles.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (doubles[i] * 100);
        }
        new TestMartix().caculator(ints);
    }

    private void caculator(int[] jianju) {
        //切割后管子的长度
        int[] guanzi = new int[1024];
        //每根管子的长度
        int k = 1200;
        for (int i = 0; i < guanzi.length; i++) {
            guanzi[i] = k;
        }
        //最小和孔洞的间隔
        int minD = 30;
        //最大和孔洞的间隔
        int maxD = 300;
        //多余出的可切可不切的长度
        int current = 0;
        //管子的数量
        int count = 1;
        //当前管子长度
        int c = 0;
        //切割次数
        int result = 0;
        //i 第n个孔洞 jianju[i] 第n个孔洞和第n+1个孔洞的间距
        for (int i = 0; i < jianju.length; i++) {
            //第一个孔洞距离前面需要留30cm
            if (i == 0) {
                c = k;
                c -= minD;
            }
            //如果第i个和第i+1个孔洞的间距小于 剩余长度 且剩余长度 大于最小的30 则可以直接越过该孔洞
            if (jianju[i] <= c + current && c + current - jianju[i] >= minD) {
                c = c - jianju[i];
                if (c < minD) {
                    current = current - (minD - c);
                    c = minD;
                }
                continue;
            }
            // 如果还是不够长 则需要新加一个管子
            else if (jianju[i] > c + current){
                // 如果管尾距离下一个孔洞小于30cm 则还需要切割
                if (jianju[i] - c < minD) {
                    current = 0;
                    int current1 = maxD - (jianju[i] - c) - minD;
                    current += current1;
                    guanzi[count] -= current1;
                    result++;
                }
                c = k + c - jianju[i];
                count++;
                continue;
            }
            // 如果够长 但是不能满足 多出30cm 则需要切割管子切割的长度 按照可以切最大切 放到余着里面
            else if (c + current - jianju[i] < minD) {
                current = 0;
                int current1 = maxD - (jianju[i] - c) - minD;
                guanzi[count] -= current1;
                current += current1;
                result++;
                c = k + c - jianju[i];
                count++;
                continue;
            }
        }
        System.out.println(result);

        for (int i = 0; i < count; i++) {
            System.out.println(guanzi[i]);
        }
    }
}
