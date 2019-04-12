package com.guman.test;

import com.guman.annotation.Wconfig;
import org.springframework.stereotype.Component;

/**
 * @author duanhaoran
 * @since 2019/3/25 4:17 PM
 */
@Component
public class ABCTest {

    @Wconfig("x")
    private String x;


    public void print(){
        System.out.println(x);
    }
}
