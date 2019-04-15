package com.guman.test;

import com.guman.annotation.Wconfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author duanhaoran
 * @since 2019/3/25 4:19 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class MainTest {

    @Resource
    private MainC mainC;

    @Resource
    private ABCTest abcTest;

    @Test
    public void test(){
        List<Class<?>> allBeans = mainC.getAllBeans();
        allBeans.stream().map(Class::getSimpleName).forEach(System.out::println);
        String value = (String)AnnotationUtils.getDefaultValue(Wconfig.class, "value");
        System.out.println(value);

    }

    @Test
    public void test2(){
        abcTest.print();
    }

    @Test
    public void test3() {
        ResourceTest test=new ResourceTest();
        test.print();
    }
}
