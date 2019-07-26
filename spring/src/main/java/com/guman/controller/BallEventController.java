package com.guman.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.util.Objects;
import java.util.logging.Handler;

/**
 * @author duanhaoran
 * @since 2019/7/24 7:23 PM
 */
@RestController
public class BallEventController extends AbstractEventController {

    @Resource
    private BallEventHandle ballEventHAndle;



    @Override
    public Handler getHandler() {
        return ballEventHAndle;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Objects.nonNull(applicationContext)) {
            applicationContext.get
        }
    }


    @Override
    public Response event() {
        return super.event();
    }
}
