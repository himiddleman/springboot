package com.allinpay.core.util;

import com.allinpay.repository.domain.ScheduleJob;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

/**
 * 任务管理模块的工具类
 */
public class QuartzUtils {

    /**
     * 基于cron调度的Job的默认组名
     */
    public static final String CRON_JOB_GROUP_NAME = "cron_task_group";

    /**
     * 产生JobKey
     *
     * @param job
     * @return
     */
    public static JobKey genCronJobKey(ScheduleJob job) {
        return new JobKey("job_" + job.getKid(), CRON_JOB_GROUP_NAME);
    }

    /**
     * 产生TriggerKey
     *
     * @param job
     * @return
     */
    public static TriggerKey genCronTriggerKey(ScheduleJob job) {
        return new TriggerKey("trigger_" + job.getKid(), CRON_JOB_GROUP_NAME);
    }

    /**
     * 判断是否两个trigger key是否相等
     *
     * @param tk1
     * @param tk2
     * @return
     */
    public static boolean isTriggerKeyEqual(TriggerKey tk1, TriggerKey tk2) {
        return tk1.getName().equals(tk2.getName()) && tk1.getGroup().equals(tk2.getGroup());
    }
}