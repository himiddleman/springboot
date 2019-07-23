package com.allinpay.core.quartz;

import com.allinpay.core.util.QuartzUtils;
import com.allinpay.repository.domain.ScheduleJob;
import com.allinpay.repository.mapper.ScheduleJobMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 基于Cron的定时任务服务类
 *
 * @author pengjunlee
 */
@Service
@Slf4j
public class TaskCronJobService {

    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    @Autowired
    private Scheduler scheduler;

    // 在对任务进行保存时需同步更新调度器中的定时任务配置
    @Transactional
    public void save(ScheduleJob taskCronJob) {
        try {
            ScheduleJob job = scheduleJobMapper.selectOne(taskCronJob.getKid());
            TriggerKey triggerKey = QuartzUtils.genCronTriggerKey(job);
            JobKey jobKey = QuartzUtils.genCronJobKey(job);
            // 如果不同则代表着CRON表达式已经修改
            if (!job.getCron().equals(taskCronJob.getCron())) {
                CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey)
                        .withSchedule(CronScheduleBuilder.cronSchedule(taskCronJob.getCron()).withMisfireHandlingInstructionDoNothing()).build();
                // 更新任务
                scheduler.rescheduleJob(triggerKey, newTrigger);
            }
            if (!job.getStatus().equals(taskCronJob.getStatus())) {
                // 如果状态为0则停止该任务
                if (!taskCronJob.getStatus().equals("0")) {
                    /* scheduler.unscheduleJob(triggerKey); */
                    scheduler.pauseJob(jobKey);
                    /* scheduler.deleteJob(jobKey); */
                } else {
                    Trigger trigger = scheduler.getTrigger(triggerKey);
                    // trigger如果为null则说明scheduler中并没有创建该任务
                    if (trigger == null) {
                        Class<?> jobClass = Class.forName(job.getJobClassname().trim());
                        @SuppressWarnings("unchecked")
                        JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) jobClass).withIdentity(jobKey)
                                .withDescription(job.getDescription()).build();
                        trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey)
                                .withSchedule(CronScheduleBuilder.cronSchedule(taskCronJob.getCron()).withMisfireHandlingInstructionDoNothing())
                                .build();

                        scheduler.scheduleJob(jobDetail, trigger);
                    } else {
                        // 不为null则说明scheduler中有创建该任务,更新即可
                        CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey)
                                .withSchedule(CronScheduleBuilder.cronSchedule(taskCronJob.getCron()).withMisfireHandlingInstructionDoNothing())
                                .build();
                        scheduler.rescheduleJob(triggerKey, newTrigger);
                    }
                }
            }
            job.setCron(taskCronJob.getCron());
            job.setStatus(taskCronJob.getStatus());

            scheduleJobMapper.save(job);
        } catch (Exception e) {
            log.error("定时任务刷新失败...");
            log.error(e.getMessage());
        }
    }
}