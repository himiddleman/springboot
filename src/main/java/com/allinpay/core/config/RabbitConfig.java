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

    /**
     * 定义一个hello的队列
     * Queue 可以有4个参数
     * 1.队列名
     * 2.durable       持久化消息队列 ,rabbitmq重启的时候不需要创建新的队列 默认true
     * 3.auto-delete   表示消息队列没有在使用时将被自动删除 默认是false
     * 4.exclusive     表示该消息队列是否只在当前connection生效,默认是false
     */
    @Bean
    public Queue helloQueue() {
        return new Queue("queue-test");
    }

    /** ======================== 定制一些处理策略 =============================*/

    /**
     * 定制化amqp模版
     * <p>
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
     * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        // 消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);

        // 消息返回, yml需要配置 publisher-returns: true
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationIdString();
            log.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        });

        // 消息确认, yml需要配置 publisher-confirms: true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.debug("消息发送到exchange成功,id: {}", correlationData.getId());
            } else {
                log.debug("消息发送到exchange失败,原因: {}", cause);
            }
        });
        return rabbitTemplate;
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
    Binding directBinding(Queue queueA, DirectExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange).with("AAA");
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
