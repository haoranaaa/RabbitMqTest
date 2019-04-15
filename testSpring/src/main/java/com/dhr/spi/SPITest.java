package com.dhr.spi;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author duanhaoran
 * @since 2019/4/13 2:31 PM
 */
public class SPITest {


    @Resource
    private InterfaceSpiTestService spiOneTestProcessor;

    public static void main(String[] args) {
        Iterator<InterfaceSpiTestService> iterator = ServiceLoader.load(InterfaceSpiTestService.class).iterator();

        while (iterator.hasNext()){
            InterfaceSpiTestService next = iterator.next();
            next.testPrint();
        }

        SPITest spiTest = new SPITest();
        System.out.println(spiTest.spiOneTestProcessor);
    }
}
