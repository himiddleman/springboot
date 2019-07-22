package com.allinpay.controller.ali;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        System.out.println(jobDetail);
        String param = (String) jobDetail.getJobDataMap().get("param");
        System.out.println(param);
        System.out.println("你好啊！定时任务启动了");
    }
}
