package com.guman.springbootguman.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author duanhaoran
 * @since 2020/4/10 1:06 PM
 */
@Aspect
@Component
public class NewGatewayAspect {

    private static Logger logger = LoggerFactory.getLogger(NewGatewayAspect.class);

    @Pointcut("@within(org.springframework.web.bind.annotation.RequestMapping)")
    public void cutAddress(){
    }

    @Around("cutAddress()")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("cut point around");
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        System.out.println(methodSignature.getMethod().getName());
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
        return null;
    }

}
