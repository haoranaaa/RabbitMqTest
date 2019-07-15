package com.guman.test;


import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author duanhaoran
 * @since 2019/3/25 4:19 PM
 */
@Lazy(false)
@Component
@EnableScheduling
public class SpringDynamicCronTask implements SchedulingConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(SpringDynamicCronTask.class);

    private volatile String cron;

    private ScheduledTaskRegistrar scheduledTaskRegistrar;

    private TriggerTask triggerTask;

    @SuppressWarnings("unchecked")
    public SpringDynamicCronTask() {
        new Thread(() -> {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Class<? extends ScheduledTaskRegistrar> clazz = scheduledTaskRegistrar.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (Objects.equals(field.getName(),"triggerTasks")) {
                    List<TriggerTask> list = (List<TriggerTask>) ReflectionUtils.getField(field, scheduledTaskRegistrar);
                    if (!CollectionUtils.isEmpty(list)) {
                        list.clear();
                    }
                }
                if (Objects.equals(field.getName(),"unresolvedTasks")) {
                    Map<Task, ScheduledTask> map =(Map<Task, ScheduledTask>) ReflectionUtils.getField(field, scheduledTaskRegistrar);
                    if (!CollectionUtils.isEmpty(map)) {
                        map.clear();
                    }
                }
            }
            Set<ScheduledTask> taskSet = scheduledTaskRegistrar.getScheduledTasks();
            for (ScheduledTask task : taskSet) {
                task.cancel();
            }

            setCron("0/10 * * * * ?");
            configureTasks(scheduledTaskRegistrar);
            scheduledTaskRegistrar.afterPropertiesSet();
            System.err.println("cron change to: " + cron);
        }).start();
    }

    public void setCron(String cron) {
        // 开启新线程模拟外部更改了任务执行周期
        this.cron = cron;
    }

    @PostConstruct
    public void init() {
        if (cron == null) {
            cron = "0/5 * * * * ?";
        }
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            // 任务逻辑
            System.out.println(new DateTime().toString());
        }, new CronTrigger(cron));
        scheduledTaskRegistrar = taskRegistrar;
    }
}