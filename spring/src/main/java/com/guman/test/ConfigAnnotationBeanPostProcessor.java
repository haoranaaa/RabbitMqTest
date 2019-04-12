package com.guman.test;

import com.guman.annotation.Wconfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * @author duanhaoran
 * @since 2019/4/1 4:23 PM
 */
public class ConfigAnnotationBeanPostProcessor implements BeanPostProcessor {

    private Logger logger=LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        parseFileds(bean ,bean.getClass());
        return bean;
    }

    private void parseFileds(Object bean, Class<?> clazz) {
        for (Field field:clazz.getDeclaredFields()){
            Wconfig wconfig = field.getAnnotation(Wconfig.class);
            if(wconfig == null){
                continue;
            }
            String value = wconfig.value();
            if(!StringUtils.hasText(value)){
                logger.error("必须指定名称！");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field,bean,value);
        }

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
