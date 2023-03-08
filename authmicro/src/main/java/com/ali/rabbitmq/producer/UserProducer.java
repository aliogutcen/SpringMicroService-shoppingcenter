package com.ali.rabbitmq.producer;

import com.ali.rabbitmq.model.ActivatedUser;
import com.ali.rabbitmq.model.CreateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;


    public void createSendMessage(CreateUser createUser) {
        rabbitTemplate.convertAndSend("exchange-direct-auth", "key", createUser);
    }

    public void updateSendMessage(ActivatedUser activatedUser) {
        rabbitTemplate.convertAndSend("exchange-direct-auth", "key-activated", activatedUser);
    }
}
