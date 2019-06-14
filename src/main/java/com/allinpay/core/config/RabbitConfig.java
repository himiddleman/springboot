package com.allinpay.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class RabbitConfig {
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
