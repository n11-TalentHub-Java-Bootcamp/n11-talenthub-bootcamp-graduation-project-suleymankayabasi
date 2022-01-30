package com.patika.creditservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String NOTIFICATION_QUEUE_NAME = "application";
    public static final String EXCHANGE = "common-exchange";
    public static final String ROUTING_KEY = "notification-routing-key";

    @Bean
    public Queue customerQueue() {
        return new Queue(NOTIFICATION_QUEUE_NAME);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding customerQueueBinding(Queue customerQueue, DirectExchange exchange) {
        return BindingBuilder.bind(customerQueue).to(exchange).with(ROUTING_KEY);
    }
}
