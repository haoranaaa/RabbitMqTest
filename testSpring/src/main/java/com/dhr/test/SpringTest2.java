package com.dhr.test;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 28 九月 2018
 */
@Component
class SpringTest2{

    @Resource SpringTest springTest;

    private AtomicLong atomicLong = new AtomicLong(0);

    protected void test(){

        springTest.test();
        System.out.println("我是坏人！");
    }

    public Long  soutUniqID(){
        return atomicLong.get();
    }
}
