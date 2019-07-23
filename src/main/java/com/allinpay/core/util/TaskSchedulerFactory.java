//package com.allinpay.core.util;
//
//import lombok.extern.slf4j.Slf4j;
//import org.quartz.Scheduler;
//import org.quartz.impl.StdSchedulerFactory;
//import org.springframework.stereotype.Component;
//
///**
// * 调度器工厂类，应当在Spring中将该类配置为单例
// */
//@Component
//@Slf4j
//public class TaskSchedulerFactory {
//
//    private static Scheduler scheduler;
//
//    /**
//     * 获得scheduler实例
//     */
//    public static Scheduler getScheduler() {
//        if (scheduler == null) {
//            synchronized (TaskSchedulerFactory.class) {
//                if (scheduler == null) {
//                    // 双重检查
//                    try {
//                        scheduler = new StdSchedulerFactory().getScheduler();
//                    } catch (Exception e) {
//                        log.error("Get scheduler error :" + e.getMessage(), e);
//                    }
//                }
//            }
//        }
//        return scheduler;
//    }
//}