package com.patika.customerservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String CUSTOMER_QUEUE_NAME = "customer";
    public static final String DELETE_QUEUE_NAME = "delete_application";
    public static final String EXCHANGE = "common-exchange";
    public static final String CUSTOMER_ROUTING_KEY = "customer-routing-key";
    public static final String DELETE_ROUTING_KEY = "delete-routing-key";

    @Bean
    public Queue customerQueue() {
        return new Queue(CUSTOMER_QUEUE_NAME);
    }

    @Bean
    public Queue deleteQueue() {
        return new Queue(DELETE_QUEUE_NAME);
    }

    @Bean
    DirectExchange customerExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding customerBinding(Queue customerQueue, DirectExchange exchange) {
        return BindingBuilder.bind(customerQueue).to(exchange).with(CUSTOMER_ROUTING_KEY);
    }

    @Bean
    Binding deleteApplicationBinding(Queue deleteQueue, DirectExchange exchange) {
        return BindingBuilder.bind(deleteQueue).to(exchange).with(DELETE_ROUTING_KEY);
    }
}