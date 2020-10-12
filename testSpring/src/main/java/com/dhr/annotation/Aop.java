package com.dhr.annotation;

import com.guman.common.constant.CommonConstants;

import java.lang.annotation.*;

/**
 * @author duanhaoran
 * @since 2019/11/2 5:08 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Aop {

}
