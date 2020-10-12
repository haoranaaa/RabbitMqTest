package com.dhr.design;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author duanhaoran
 * @since 2019/11/5 5:44 PM
 */
public class FeatureService {

    public static final Map<Class<?>, Feature> map = Maps.newHashMap();


    public void filter(Context context) {
        map.get(context.getClass()).filter(context);
    }

}
