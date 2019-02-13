package com.dhr.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author haoran.duan
 * @since 28 九月 2018
 */
@Aspect
public class SpringTest {
    @Pointcut("execution(* com.dhr.test..test*(..))")
    public void testPoint() {
    }

    @After("testPoint()")
    public void aop(JoinPoint joinPoint) {
        System.out.println("Before method!");
    }


    public void test() {
        System.out.println("我是好人！");
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        SpringTest springTest = (SpringTest) applicationContext.getBean("springTest");
        springTest.test();
        SpringTest2 springTest2 = (SpringTest2) applicationContext.getBean("springTest2");
        springTest2.test();
        System.out.println(AopUtils.isAopProxy(springTest));
        System.out.println(AopUtils.isAopProxy(springTest2));
    }
}
