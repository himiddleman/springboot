package com.allinpay.core.quartz;

import com.allinpay.core.exception.AllinpayException;
import com.allinpay.core.util.QuartzUtils;
import com.allinpay.repository.domain.ScheduleJob;
import com.allinpay.repository.mapper.ScheduleJobMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

import static org.quartz.CronExpression.isValidExpression;

/**
 * 定时任务初始化服务类
 */
@Component
@Slf4j
public class QuartzInitService {

    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    @Autowired
    private Scheduler scheduler;

    /**
     * 初始化, 容器启动时执行，以后每10分钟扫描数据库一次，查找未运行的任务。
     */
    @PostConstruct
    @Scheduled(fixedDelay = 6000)
    public void init() {
        if (Objects.isNull(scheduler)) {
            log.error("初始化定时任务组件失败，Scheduler is null...");
            return;
        }

        // 初始化基于cron时间配置的任务列表
        initCronJobs(scheduler);

        try {
            if (!scheduler.isStarted()) {
                log.info("The scheduler is starting...");
                scheduler.start();
            }
        } catch (Exception e) {
            log.error("The scheduler start is error", e);
            throw new AllinpayException("50005", "任务调度器启动失败！");
        }
    }

    /**
     * 初始化任务（基于cron触发器）
     */
    private void initCronJobs(Scheduler scheduler) {
        List<ScheduleJob> jobList = scheduleJobMapper.selectList();
        jobList.forEach(job -> {
            try {
                scheduleCronJob(job, scheduler);
            } catch (ClassNotFoundException e) {
                log.error("类全限定名路径有误，请检查！【{}】", job.getJobClassname());
                throw new AllinpayException("50006", "未找到指定的class文件！");
            } catch (SchedulerException e) {
                log.error("ScheduleCronJob is error", e);
                throw new AllinpayException("50007", "任务调度异常！");
            }
        });
    }

    /**
     * 安排任务(基于cron触发器)
     *
     * @param job
     * @param scheduler
     */
    private void scheduleCronJob(ScheduleJob job, Scheduler scheduler) throws ClassNotFoundException, SchedulerException {
        if (StringUtils.isNotBlank(job.getJobClassname()) && StringUtils.isNotBlank(job.getCron())) {
            //过滤已执行任务
            if ("1".equals(job.getStatus())) {
                return;
            }

            JobKey jobKey = QuartzUtils.genCronJobKey(job);

            if (!scheduler.checkExists(jobKey)) {
                // This job doesn't exist, then add it to scheduler.
                log.info("Add new cron job to scheduler, jobId = 【{}】", job.getKid());
                newJobAndNewCronTrigger(job, scheduler, jobKey);
            } else {
                //当定时服务已启动，修改数据库定时任务cron配置时更新trigger信息，定时执行任务
                updateCronTriggerOfJob(job, scheduler, jobKey);
            }
        } else {
            log.error("Method scheduleCronJob arguments are invalid.");
            throw new IllegalArgumentException();
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
    private void newJobAndNewCronTrigger(ScheduleJob job, Scheduler scheduler, JobKey jobKey)
            throws SchedulerException, ClassNotFoundException {
        TriggerKey triggerKey = QuartzUtils.genCronTriggerKey(job);

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
        //设置触发器，绑定对应的任务
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
    private void updateCronTriggerOfJob(ScheduleJob job, Scheduler scheduler, JobKey jobKey) throws ClassNotFoundException, SchedulerException {
        TriggerKey triggerKey = QuartzUtils.genCronTriggerKey(job);
        String cronExpr = job.getCron().trim();

        List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);

        for (int i = 0; CollectionUtils.isNotEmpty(triggers) && i < triggers.size(); i++) {
            Trigger trigger = triggers.get(i);
            TriggerKey curTriggerKey = trigger.getKey();

            if (QuartzUtils.isTriggerKeyEqual(triggerKey, curTriggerKey)) {
                if (trigger instanceof CronTrigger
                        && cronExpr.equalsIgnoreCase(((CronTrigger) trigger).getCronExpression())) {
                    // do nothing
                } else {
                    if (isValidExpression(cronExpr)) {
                        // Cron expression is valid, build a new trigger and replace the old one.
                        CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey)
                                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpr)
                                        .withMisfireHandlingInstructionDoNothing())
                                .build();
                        //设置参数
                        newTrigger.getJobDataMap().put("ScheduleJob", job);

                        scheduler.rescheduleJob(curTriggerKey, newTrigger);
                        log.info("Update cron job to scheduler, jobId = 【{}】", job.getKid());
                    }
                }
            } else {
                // different trigger key ,The trigger key is illegal, unschedule this trigger
                //从任务调度器schedule中删除该触发器trigger
                scheduler.unscheduleJob(curTriggerKey);
            }
        }
    }
}