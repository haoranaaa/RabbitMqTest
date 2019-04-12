package com.dhr.test;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 17 二月 2019
 */
public class ObjectTest {

    public static void main(String[] args) throws InterruptedException {
        Object obj=new Object();
        new Thread(()->{
            synchronized (obj){
                try {
                    System.out.println("获取到锁");
                    obj.wait();
                    System.out.println("唤醒完成！");
                } catch (InterruptedException e) {
                    System.out.println("唤醒当前线程");
                }
            }
        }).start();
        new Thread(()->{
           synchronized (obj){
               try {
                   obj.notifyAll();
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   System.out.println("唤醒当前线程");
               }
               System.out.println("释放当前对象资源！");
           }

        }).start();

    }
}
