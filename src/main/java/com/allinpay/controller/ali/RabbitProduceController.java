package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RabbitProduceController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/delayDirect")
    public ResponseData deayDirect() {
        rabbitTemplate.convertAndSend("directExchange", "delayAAA", "延时队列", message -> {
            //设置消息的有效期ttl为20s
            message.getMessageProperties().setExpiration(5 * 1000 + "");
            return message;
        });
        return ResponseData.success().setData("延时队列direct交换机测试！");
    }

    @RequestMapping("/direct")
    public ResponseData direct() {
        rabbitTemplate.convertAndSend("directExchange", "AAA", "默认方式的交换机");
        return ResponseData.success().setData("direct交换机测试！");
    }

    @RequestMapping("/topic")
    public ResponseData topic() {
        rabbitTemplate.convertAndSend("topicExchange", "aa.b.E", "topic交换机 * 测试");
        rabbitTemplate.convertAndSend("topicExchange", "aa.b.aaa.d", "topic交换机 # 测试");
        return ResponseData.success().setData("topic交换机测试！");
    }

    @RequestMapping("/headers")
    public ResponseData headers() {
        Map map = new HashMap<String, Object>();
        map.put("name", "tg");
        MessageProperties props = MessagePropertiesBuilder.newInstance().setHeader("name", "tg").build();
        Message message = MessageBuilder.withBody(map.toString().getBytes()).andProperties(props).build();
        rabbitTemplate.convertAndSend("headersExchange", "", message);
        return ResponseData.success().setData("headers交换机测试！");
    }

    @RequestMapping("/fanout")
    public ResponseData fanout() {
        rabbitTemplate.convertAndSend("fanoutExchange", "", "广播模式交换机");
        return ResponseData.success().setData("fanout交换机测试！");
    }
}
