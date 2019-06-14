package com.allinpay.controller.ali;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RabbitListener(queues = {"queueB", "queueC"})
@Slf4j
@RestController
public class RabbitConsumeTopicController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    public void receiveStr(String mqStr) {
        System.out.println(mqStr);
        log.info(mqStr);
    }
}
