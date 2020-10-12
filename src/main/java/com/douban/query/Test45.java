package com.douban.query;

/**
 * @author duanhaoran
 * @since 2020/2/29 9:18 PM
 */
public class Test45 {

    private static final int MARK = 8;

    private static final int MAX_0_INTEGER_SIZE = Integer.SIZE;

    public static void main(String[] args) {
        int score = 955;

        int level = 5;

        int finScore = level << (MAX_0_INTEGER_SIZE - MARK) | score;

        int finScore2 = level << (MAX_0_INTEGER_SIZE - MARK) | (score - 1);

        System.out.println(finScore > finScore2);

        int level2 = finScore >> (MAX_0_INTEGER_SIZE - MARK);

        System.out.println(level2);

        int score2 = finScore << MARK >> MARK;

        System.out.println(score2);

        int k = 0;

        for (int i = 0; i < MARK; i++) {
            k = 1 << (MAX_0_INTEGER_SIZE - 1 - i) | k;
        }
        System.out.println(k);

        System.out.println(Integer.toBinaryString(k));

    }
}
