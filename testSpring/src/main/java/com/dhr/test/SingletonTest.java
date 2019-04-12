package com.dhr.test;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 11 二月 2019
 */
public class SingletonTest {

    private static volatile ThisTest thisTest;

    public static final String a="a";

    private static ThisTest getThisTest(){
        if(thisTest==null){
            synchronized (SingletonTest.class){
                thisTest=new ThisTest();
            }
        }
        return thisTest;
    }

    public static void getA(){
        return;
    }

}
class ThisTest extends SingletonTest{

    public static final String a="2";

    public static void getA(){
        return;
    }
}
interface test{
    abstract int aa();
}
