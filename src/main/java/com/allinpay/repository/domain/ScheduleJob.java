package com.allinpay.repository.domain;

import lombok.Data;
import org.springframework.context.Lifecycle;

import java.util.Date;

@Data
public class ScheduleJob implements Lifecycle {
    private MyScheduleJob testA;
    /**
     * 任务id
     */
    private Integer kid;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务全路径名
     */
    private String jobClassname;
    /**
     * 任务描述
     */
    private String description;
    /**
     * 任务参数
     */
    private String param;
    /**
     * cron表达式，执行任务的时间
     */
    private String cron;
    /**
     * 任务状态 0 代表未执行，1 代表已执行
     */
    private String status;
    /**
     * 任务插入时间
     */
    private Date insertTime;

    @Override
    public void start() {
        System.out.println("scheduleJob start...");
    }

    @Override
    public void stop() {
        System.out.println("scheduleJob start...");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
