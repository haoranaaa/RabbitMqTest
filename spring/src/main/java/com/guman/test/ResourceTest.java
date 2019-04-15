package com.guman.test;

import javax.annotation.Resource;

/**
 * @author duanhaoran
 * @since 2019/4/13 3:15 PM
 */
public class ResourceTest {

    @Resource
    public ABCTest abcTest;

    public void print(){
        System.out.println(abcTest);
    }
}
