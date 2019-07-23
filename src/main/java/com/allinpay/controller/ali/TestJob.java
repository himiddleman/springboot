package com.allinpay.controller.ali;

import com.allinpay.repository.domain.ScheduleJob;
import com.allinpay.service.ILoginService;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TestJob extends QuartzJobBean {
    @Autowired
    private ILoginService loginService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        System.out.println(jobDetail);

        ScheduleJob param1 = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get("ScheduleJob");
        System.out.println(param1);
        System.out.println("testJobq启动了！");
    }
}
