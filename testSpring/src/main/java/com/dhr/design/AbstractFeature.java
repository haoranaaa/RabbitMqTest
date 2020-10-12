package com.dhr.design;

import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @author duanhaoran
 * @since 2019/11/5 5:45 PM
 */
public abstract class AbstractFeature<T extends Context> implements Feature<T>,InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        Class<? extends AbstractFeature> aClass = getClass();
        for (Method method : aClass.getDeclaredMethods()) {
            if (Objects.equals(method.getName(), "filter")) {
                Type type = method.getGenericParameterTypes()[0];
                FeatureService.map.put(Class.forName(type.getTypeName()), this);
            }
        }
    }

}
