package com.allinpay.controller.ali;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RabbitListener(queues = "queueA")
@Slf4j
@RestController
public class RabbitConsumeDirectController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    public void receiveStr(Message message, Channel channel) {
        //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println(new String(message.getBody()));
            log.info(new String(message.getBody()));
        } catch (IOException e) {
            e.printStackTrace();
            try {
                //丢弃这条消息
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
