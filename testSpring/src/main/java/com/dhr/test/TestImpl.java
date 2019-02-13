package com.dhr.test;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 29 九月 2018
 */
public class TestImpl implements Test {
    @Override
    public void test1() {
        System.out.println("我正在执行Test1。。。。。");
    }

    @Override
    public void test2() {
        System.out.println("我正在执行Test2。。。。。");
    }

    protected void test3(){
        System.out.println("我正在执行Test3。。。。。");
    }
}