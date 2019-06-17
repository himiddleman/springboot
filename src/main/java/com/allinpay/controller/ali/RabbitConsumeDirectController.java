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
    public void receiveStr(String str, Message message, Channel channel) {
        log.info(str);
        //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
        //basicAck(p1,p2) 参数一表示分发标记数，参数二表示是否支持ack多个消息。false表示不支持
        boolean flag = true;
        try {
            if (flag) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                System.out.println(new String(message.getBody()));
                log.info(new String(message.getBody()));
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                log.info("进行轮询操作");
                rabbitTemplate.convertAndSend("delayExchange", "delayAAA", "延时队列", message1 -> {
                    //设置消息的有效期ttl为20s
                    message1.getMessageProperties().setExpiration(5 * 1000 + "");
                    return message1;
                });
            }
        } catch (Exception e) {
            try {
                //丢弃这条消息
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                //参数一表示分发标记数，参数二表示是否支持ack多个消息,参数三表示是否重入队列 true表示重入队列 false表示删除
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
