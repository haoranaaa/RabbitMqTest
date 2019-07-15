package com.dhr.compute;

import java.util.Random;

/**
 * @author duanhaoran
 * @since 2019/5/5 10:47 AM
 */
public class RandomTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(new Random().nextInt(10));
        }
    }
}
