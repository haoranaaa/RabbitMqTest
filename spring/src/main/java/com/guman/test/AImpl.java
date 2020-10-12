package com.guman.test;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author duanhaoran
 * @since 2019/7/1 4:59 PM
 */
public class AImpl implements TestInterface {
    String abc;
    Integer bcd;
    Date efg;
    public AImpl(String abc,Integer bcd, Date efg) {
        this.abc = abc;
        this.bcd = bcd;
        this.efg = efg;
    }

    @Override
    public String getStr() {
        return abc + " " + bcd + " " + efg.toString();
    }
}
