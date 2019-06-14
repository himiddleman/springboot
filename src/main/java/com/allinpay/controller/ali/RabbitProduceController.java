package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import com.allinpay.core.util.RabbitUtil;
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

    @RequestMapping("/direct")
    public ResponseData direct() {
        rabbitTemplate = RabbitUtil.rabbitTemplate(rabbitTemplate);
        rabbitTemplate.convertAndSend("directExchange", "AAA", "默认方式的交换机");
        return new ResponseData().success("direct交换机测试！");
    }

    @RequestMapping("/topic")
    public ResponseData topic() {
        rabbitTemplate.convertAndSend("topicExchange", "aa.b.E", "topic交换机 * 测试");
        rabbitTemplate.convertAndSend("topicExchange", "aa.b.aaa.d", "topic交换机 # 测试");
        return new ResponseData().success("topic交换机测试！");
    }

    @RequestMapping("/headers")
    public ResponseData headers() {
        Map map = new HashMap<String, Object>();
        map.put("name", "tg");
        MessageProperties props = MessagePropertiesBuilder.newInstance().setHeader("name", "tg").build();
        Message message = MessageBuilder.withBody(map.toString().getBytes()).andProperties(props).build();
        rabbitTemplate.convertAndSend("headersExchange", "", message);
        return new ResponseData().success("headers交换机测试！");
    }

    @RequestMapping("/fanout")
    public ResponseData fanout() {
        rabbitTemplate.convertAndSend("fanoutExchange", "", "广播模式交换机");
        return new ResponseData().success("fanout交换机测试！");
    }
}
