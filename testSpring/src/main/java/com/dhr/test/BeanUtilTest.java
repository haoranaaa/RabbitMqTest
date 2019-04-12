package com.dhr.test;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author duanhaoran
 * @since 2019/4/2 10:57 AM
 */
public class BeanUtilTest {

    public static void main(String[] args) {
        Config config = Config.builder().aTest(Lists.newArrayList("a","b")).a("c").build();
        try {
            Map<String, String> describe = BeanUtils.describe(config);
            System.out.println(describe);
            Map<String, String> describe1 = describe(config);
            System.out.println(describe1);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static Map<String,String> describe(Object obj){
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getMethods();
        Map<String,String> map= Maps.newHashMap();
        for (Method method:methods){
            if (method.getName().startsWith("get") && !method.getName().endsWith("Class")){
                try {
                    Object invoke = method.invoke(obj);
                    if(invoke == null){
                        continue;
                    }
                    map.put(method.getName(),invoke.toString());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field field:declaredFields) {
            field.setAccessible(true);
            try {
                Object o = field.get(obj);
                map.put(field.getName(), o == null ? "null" : o.toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }


}
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Config{
    private List<String> aTest;

    private String a;
}
