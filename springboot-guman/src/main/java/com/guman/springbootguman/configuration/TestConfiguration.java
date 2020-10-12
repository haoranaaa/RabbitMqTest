package com.guman.springbootguman.configuration;

import com.guman.springbootguman.TestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.DispatcherType;

/**
 * @author duanhaoran
 * @since 2020/4/7 6:30 PM
 */
@Configuration
public class TestConfiguration {

    @Resource
    private TestFilter testFilter;

//    @Bean
//    public FilterRegistrationBean registrationProjectFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
////        registration.setFilter(testFilter);
////        registration.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.values());
////        registration.addUrlPatterns("/test/b");
////        registration.setOrder(2);
//        return registration;
//    }

}
