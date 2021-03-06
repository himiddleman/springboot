package com.allinpay.controller.ali;

import com.allinpay.repository.domain.ScheduleJob;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        System.out.println(jobDetail);
        ScheduleJob param = (ScheduleJob) jobDetail.getJobDataMap().get("ScheduleJob");
        System.out.println(param);
        System.out.println("myJobq启动了");
    }
}
