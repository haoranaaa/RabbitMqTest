package com.guman.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author duanhaoran
 * @since 2019/4/1 4:42 PM
 */
@Configuration
public class WconfigConfigration implements ImportAware {


    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {

    }

    @Bean
    public ConfigAnnotationBeanPostProcessor wconfigAnnotationBeanPostProcessor() {
        return new ConfigAnnotationBeanPostProcessor();
    }
}
