package com.allinpay.core.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * 不配置的话自定义的job类无法注入其他的类，如service类
 */
@Component
public class JobFactory extends AdaptableJobFactory {
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 调用父类的方法    
        Object jobInstance = super.createJobInstance(bundle);
        // 进行注入    
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}