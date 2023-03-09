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

    private String keyMail = "key-mail";
    private String mailQueque = "mail-queque";

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    DirectExchange directExchange1() {
        return new DirectExchange(exchange);
    }

    @Bean
    Queue createAuthQueque() {
        return new Queue(authQueque);
    }

    @Bean
    Queue updateAuthQueque() {
        return new Queue(authQuequeActivated);
    }

    @Bean
    Queue createMailQueque() {
        return new Queue(mailQueque);
    }

    @Bean
    Binding bindingCreateMail(DirectExchange directExchange, Queue createMailQueque) {
        return BindingBuilder.bind(createMailQueque).to(directExchange).with(keyMail);
    }

    @Bean
    Binding bindingCreateAuth(DirectExchange directExchange, Queue createAuthQueque) {
        return BindingBuilder.bind(createAuthQueque).to(directExchange).with(keyAuth);
    }

    @Bean
    Binding bindingUpdateAuth(DirectExchange directExchange, Queue updateAuthQueque) {
        return BindingBuilder.bind(updateAuthQueque).to(directExchange).with(keyAuthActivated);
    }


}
