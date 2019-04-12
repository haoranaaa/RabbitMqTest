package com.guman.test;

import com.google.common.collect.Lists;
import com.guman.annotation.Wconfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author duanhaoran
 * @since 2019/3/25 4:19 PM
 */
@Component
public class MainC implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Resource
    private ABCTest abcTest;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public List<Class<?>> getAllBeans(){
        List<Class<?>> clazzs=Lists.newArrayList();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String beanName:beanDefinitionNames){
            Class<?> beanType=applicationContext.getType(beanName);
            clazzs.add(beanType);
        }
        return clazzs;
    }

    public void innerConfig(){
        List<Class<?>> allBeans = getAllBeans();
        for (Class<?> clazz:allBeans) {
            Field[] fields = clazz.getFields();
            if(fields.length == 0){
                continue;
            }
            for(Field field:fields){
                Wconfig wconfig = field.getAnnotation(Wconfig.class);

            }
        }
    }

}
