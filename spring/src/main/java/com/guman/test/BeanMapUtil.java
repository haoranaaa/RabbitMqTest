package com.guman.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.guman.common.json.JSON;
import com.guman.common.json.joda.JacksonDateFormat;
import com.guman.model.ModelA;
import org.springframework.cglib.beans.BeanMap;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author duanhaoran
 * @since 2019/7/13 3:10 PM
 */
public class BeanMapUtil {


    public static void main(String[] args) throws IOException {
        ModelA modelA = new ModelA();
        modelA.setA(1);
        modelA.setB("2");
        modelA.setC(2000L);
        modelA.setDate(new Date());
        Map<String, Object> map = beanToMap(modelA);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(map);
        ModelA modelA1 = objectMapper.readValue(s, ModelA.class);
        System.out.println(s);
    }




    /**
     * 将对象装换为map
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map,T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

}
