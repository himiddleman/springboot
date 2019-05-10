package com.allinpay.core.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务配置
 */
@Slf4j
@Component
@PropertySource("classpath:resource.properties")
public class ScheduleJobConfig implements CommandLineRunner {
    //模拟支付处理中状态 定时查询订单状态
    @Scheduled(cron = "${cron.test}")
    public void print() throws Exception {
//        System.out.println("模拟支付处理中状态 定时查询订单状态~~");
//        SybOnlinePayController controller = new SybOnlinePayController();
//        controller.dealProcess();
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("我启动后就打印信息~~");
    }
}
