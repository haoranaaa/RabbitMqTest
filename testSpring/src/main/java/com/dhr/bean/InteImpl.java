package com.dhr.bean;

import com.guman.common.json.JSON;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * @author duanhaoran
 * @since 2019/9/12 1:02 PM
 */
public class InteImpl extends InteTest {
    @Override
    public void testAnnotation() {

    }

    public static void main(String[] args) {
        InteImpl inte = new InteImpl();
        Method[] methods = inte.getClass().getMethods();
        for (Method me : methods) {
            System.out.println(me.getName() + ": " + JSON.writeValueAsString(me.getAnnotation(Resource.class)));
        }
    }
}
