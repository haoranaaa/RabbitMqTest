package com.dhr.lock;

public class SychorinzedTest {

    public synchronized void test1(){
        System.out.println("test1");
    }

    public void test2(){
        synchronized (SychorinzedTest.class) {
            test1();
            System.out.println("test2");
        }
    }

    public static void main(String[] args) {
        SychorinzedTest test=new SychorinzedTest();
        test.test2();
    }
}
