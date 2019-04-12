package com.guman.common.annotate;

import java.lang.annotation.*;

/**
 * @author duanhaoran
 * @since 2019/4/1 5:04 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {
    String value() default "";
}
