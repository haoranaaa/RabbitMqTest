package com.dhr.designPattern.annotation;

import java.lang.annotation.*;

/**
 * @author duanhaoran
 * @since 2019/12/1 11:38 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Clazz {

    int order() default 0;

}
