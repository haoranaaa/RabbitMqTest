package com.dhr.spi;

/**
 * @author duanhaoran
 * @since 2019/4/13 2:30 PM
 */
public class SpiOneTestProcessor implements InterfaceSpiTestService {
    @Override
    public void testPrint() {
        System.out.println("这是第二个类");
    }
}
