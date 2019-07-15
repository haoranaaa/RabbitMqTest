package com.guman.test;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author duanhaoran
 * @since 2019/3/25 4:16 PM
 */
@Component
public class HandlerTest {

    public void print() {
        System.out.println("hi");
    }
}
