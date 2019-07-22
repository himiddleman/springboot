package com.allinpay.core.schedule;

import com.allinpay.core.util.TaskSchedulerFactory;
import com.allinpay.core.util.TaskUtils;
import com.allinpay.repository.domain.ScheduleJob;
import com.allinpay.repository.mapper.ScheduleJobMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.quartz.CronExpression.isValidExpression;

/**
 * 定时任务初始化服务类
 *
 * @author pengjunlee
 */
@Component
@Slf4j
public class TaskInitService {

    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    @Autowired
    private TaskSchedulerFactory schedulerFactory;

    /**
     * 初始化
     */
    @PostConstruct
    @Scheduled(fixedRate = 10000)
    public void init() {
        Scheduler scheduler = schedulerFactory.getScheduler();
        if (scheduler == null) {
            log.error("初始化定时任务组件失败，Scheduler is null...");
            return;
        }

        // 初始化基于cron时间配置的任务列表
        try {
            initCronJobs(scheduler);
        } catch (Exception e) {
            log.error("init cron tasks error," + e.getMessage(), e);
        }

        try {
            log.info("The scheduler is starting...");
            scheduler.start(); // start the scheduler
        } catch (Exception e) {
            log.error("The scheduler start is error," + e.getMessage(), e);
        }
    }

    /**
     * 初始化任务（基于cron触发器）
     */
    private void initCronJobs(Scheduler scheduler) throws Exception {
        Iterable<ScheduleJob> jobList = scheduleJobMapper.selectList();
        if (jobList != null) {
            for (ScheduleJob job : jobList) {
                scheduleCronJob(job, scheduler);
            }
        }
    }

    /**
     * 安排任务(基于cron触发器)
     *
     * @param job
     * @param scheduler
     */
    private void scheduleCronJob(ScheduleJob job, Scheduler scheduler) {
        if (job != null && StringUtils.isNotBlank(job.getJobName()) && StringUtils.isNotBlank(job.getJobClassname())
                && StringUtils.isNotBlank(job.getCron()) && scheduler != null) {
            if (!job.getStatus().equals("0")) {
                return;
            }

            try {
                JobKey jobKey = TaskUtils.genCronJobKey(job);

                if (!scheduler.checkExists(jobKey)) {
                    // This job doesn't exist, then add it to scheduler.
                    log.info("Add new cron job to scheduler, jobName = " + job.getJobName());
                    this.newJobAndNewCronTrigger(job, scheduler, jobKey);
                } else {
                    log.info("Update cron job to scheduler, jobName = " + job.getJobName());
                    this.updateCronTriggerOfJob(job, scheduler, jobKey);
                }
            } catch (Exception e) {
                log.error("ScheduleCronJob is error," + e.getMessage(), e);
            }
        } else {
            log.error("Method scheduleCronJob arguments are invalid.");
        }
    }

    /**
     * 新建job和trigger到scheduler(基于cron触发器)
     *
     * @param job
     * @param scheduler
     * @param jobKey
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void newJobAndNewCronTrigger(ScheduleJob job, Scheduler scheduler, JobKey jobKey)
            throws SchedulerException, ClassNotFoundException {
        TriggerKey triggerKey = TaskUtils.genCronTriggerKey(job);

        String cronExpr = job.getCron();
        if (!isValidExpression(cronExpr)) {
            return;
        }

        // get a Class object by string class name of job;
        Class jobClass = Class.forName(job.getJobClassname().trim());
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).withDescription(job.getDescription())
                .build();
        //设置参数
        jobDetail.getJobDataMap().put("ScheduleJob", job);
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpr).withMisfireHandlingInstructionDoNothing())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 更新job的trigger(基于cron触发器)
     *
     * @param job
     * @param scheduler
     * @param jobKey
     * @throws SchedulerException
     */
    private void updateCronTriggerOfJob(ScheduleJob job, Scheduler scheduler, JobKey jobKey) throws SchedulerException {
        TriggerKey triggerKey = TaskUtils.genCronTriggerKey(job);
        String cronExpr = job.getCron().trim();

        List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);

        for (int i = 0; triggers != null && i < triggers.size(); i++) {
            Trigger trigger = triggers.get(i);
            TriggerKey curTriggerKey = trigger.getKey();

            if (TaskUtils.isTriggerKeyEqual(triggerKey, curTriggerKey)) {
                if (trigger instanceof CronTrigger
                        && cronExpr.equalsIgnoreCase(((CronTrigger) trigger).getCronExpression())) {
                    // Don't need to do anything.
                } else {
                    if (isValidExpression(job.getCron())) {
                        // Cron expression is valid, build a new trigger and
                        // replace the old one.
                        CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey)
                                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpr)
                                        .withMisfireHandlingInstructionDoNothing())
                                .build();
                        scheduler.rescheduleJob(curTriggerKey, newTrigger);
                    }
                }
            } else {
                // different trigger key ,The trigger key is illegal, unschedule
                // this trigger
                scheduler.unscheduleJob(curTriggerKey);
            }
        }
    }
}