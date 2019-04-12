package com.guman.annotation;

import java.lang.annotation.*;

/**
 * @author duanhaoran
 * @since 2019/3/25 4:31 PM
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Wconfig {

    String value() default "null";

    String applicationName() default "";

}
