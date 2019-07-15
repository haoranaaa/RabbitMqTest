package com.guman.test;

import org.springframework.stereotype.Component;

/**
 * @author duanhaoran
 * @since 2019/5/8 12:17 PM
 */
@Component
public interface interfaceTest {

    default Integer getInt(){
        System.out.println(1);
        return 1;
    }
}
