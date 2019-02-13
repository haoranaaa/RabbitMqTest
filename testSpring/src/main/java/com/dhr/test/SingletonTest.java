package com.dhr.test;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 11 二月 2019
 */
public class SingletonTest {

    private static volatile ThisTest thisTest;

    private static ThisTest getThisTest(){
        if(thisTest==null){
            synchronized (SingletonTest.class){
                thisTest=new ThisTest();
            }
        }
        return thisTest;
    }

}
class ThisTest extends SingletonTest{


    public void test(){

    }
}
