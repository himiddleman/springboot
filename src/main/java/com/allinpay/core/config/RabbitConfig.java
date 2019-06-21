package com.allinpay.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class RabbitConfig {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Bean
    public AmqpTemplate amqpTemplate() {
        //消息是否发送到指定的队列
        final boolean[] sendToQueue = {true};
        //使用jackson 消息转换器
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        rabbitTemplate.setEncoding("UTF-8");
        //开启returncallback yml 需要 配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationIdString();
            log.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
            sendToQueue[0] = false;
        });
        //消息确认 yml 需要配置  publisher-returns: true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息发送到exchange成功,不一定发送到了指定的队列");
                if (sendToQueue[0]) {
                    log.info("消息发送到queue成功");
                }
            } else {
                log.info("消息发送到exchange失败,原因: {}", cause);
            }
            sendToQueue[0] = true;
        });
        return rabbitTemplate;
    }

    //延时队列
    @Bean
    public Queue delayProcessQueue() {
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的死信交换机，
        params.put("x-dead-letter-exchange", "directExchange");
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的路由键。
        params.put("x-dead-letter-routing-key", "AAA");
        return new Queue("delayProcessQueue", true, false, false, params);
    }

    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange("delayExchange");
    }

    @Bean
    public Binding dlxBinding(Queue delayProcessQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(delayProcessQueue).to(directExchange).with("delayAAA");
    }

    //创建列队对象
    @Bean
    public Queue queueA() {
        return new Queue("queueA");
    }

    @Bean
    public Queue queueB() {
        return new Queue("queueB");
    }

    @Bean
    public Queue queueC() {
        return new Queue("queueC");
    }

    @Bean
    public Queue queueD() {
        return new Queue("queueD");
    }

    @Bean
    public Queue queueE() {
        return new Queue("queueE");
    }

    @Bean
    public Queue queueF() {
        return new Queue("queueF");
    }

    //创建direct交换机
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }


    @Bean
    Binding directBinding(Queue queueA, DirectExchange directExchange) {
        return BindingBuilder.bind(queueA).to(directExchange).with("AAA");
    }

    //创建topic交换机
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding topicBindingA(Queue queueB, TopicExchange exchange) {
        return BindingBuilder.bind(queueB).to(exchange).with("aa.b.*");
    }

    @Bean
    Binding topicBindingB(Queue queueC, TopicExchange exchange) {
        return BindingBuilder.bind(queueC).to(exchange).with("aa.b.#.d");
    }

    //创建headers交换机
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("headersExchange");
    }

    @Bean
    Binding headersBinding(Queue queueD, HeadersExchange exchange) {
        Map map = new HashMap<String, Object>();
        map.put("name", "tg");
        return BindingBuilder.bind(queueD).to(exchange).whereAll(map).match();
    }

    //创建fanout交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding fanoutBindingA(Queue queueE, FanoutExchange exchange) {
        return BindingBuilder.bind(queueE).to(exchange);
    }

    @Bean
    Binding fanoutBindingB(Queue queueF, FanoutExchange exchange) {
        return BindingBuilder.bind(queueF).to(exchange);
    }
}
