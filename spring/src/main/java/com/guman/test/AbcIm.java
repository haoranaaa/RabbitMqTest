package com.guman.test;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author duanhaoran
 * @since 2019/4/24 5:43 PM
 */
@Component("abcIm")
public class AbcIm extends ABCTest {

    @Override
    public void print() {
        test.print();
    }

    private static int k = 3;
}

