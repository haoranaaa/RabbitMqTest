package com.example.bootstrap.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author duanhaoran
 * @since 2019/7/9 10:50 AM
 */
@Component
@EnableScheduling
public class TestSchedule {

    @PostConstruct
    private void init() {
        System.out.println("初始化schedule");
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void testJob() {
        System.out.println(System.currentTimeMillis());
    }
}
