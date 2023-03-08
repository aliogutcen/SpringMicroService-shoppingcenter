package com.ali.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private String exchange = "exchange-direct-auth";
    private String authQueque = "queque-create-auth";
    private String authQuequeActivated = "queque-activated-auth";
    private String keyAuth = "key";
    private String keyAuthActivated = "key-activated";

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Queue createAuthQueque() {
        return new Queue(authQueque);
    }



    @Bean
    Binding bindingCreateAuth(final DirectExchange exchange, final Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(keyAuth);
    }
}
