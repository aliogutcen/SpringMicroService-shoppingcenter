package com.ali.rabbitmq.consumer;

import com.ali.rabbitmq.model.MailCreate;
import com.ali.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;
    @RabbitListener(queues = "mail-queque")
    public void createMail(MailCreate mailCreate){
        emailService.save(mailCreate);
    }
}
