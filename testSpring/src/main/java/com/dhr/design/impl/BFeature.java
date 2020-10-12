package com.dhr.design.impl;

import com.dhr.design.AbstractFeature;

/**
 * @author duanhaoran
 * @since 2019/11/5 5:52 PM
 */
public class BFeature extends AbstractFeature<BContext> {

    @Override
    public void filter(BContext bContext) {
        System.out.println("b filter");
    }
}
