package com.guman.config.client.spring.annotation;

import com.guman.common.constant.CommonConstants;

import java.lang.annotation.*;

/**
 * @author duanhaoran
 * @since 2019/4/16 3:03 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Config {

    String appName() default CommonConstants.STRING_EMPTY;

    String value();
}