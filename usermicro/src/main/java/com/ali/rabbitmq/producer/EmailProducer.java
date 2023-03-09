package com.ali.rabbitmq.producer;

import com.ali.rabbitmq.model.MailCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;


    public void createMail(MailCreate mailCreate){
        rabbitTemplate.convertAndSend("exchange-direct-auth","key-mail",mailCreate);
    }
}
