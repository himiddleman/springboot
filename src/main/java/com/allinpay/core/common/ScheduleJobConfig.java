package com.allinpay.core.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 定时任务配置
 */
@Slf4j
@Component
@PropertySource("classpath:resource.properties")
public class ScheduleJobConfig implements CommandLineRunner {
//    @Scheduled(cron = "${cron.test}")
//    public void print() {
//        System.out.println("我由定时任务调度打印信息~~");
//    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("我启动后就打印信息~~");
    }
}
