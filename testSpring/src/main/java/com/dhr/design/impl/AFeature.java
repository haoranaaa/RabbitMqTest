package com.dhr.design.impl;

import com.dhr.design.AbstractFeature;

/**
 * @author duanhaoran
 * @since 2019/11/5 5:52 PM
 */
public class AFeature extends AbstractFeature<AContext> {

    @Override
    public void filter(AContext aContext) {
        System.out.println("a filter");
    }
}
