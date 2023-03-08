package com.ali.rabbitmq.consumer;

import com.ali.rabbitmq.model.CreateUser;
import com.ali.repository.entity.UserProfile;
import com.ali.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    private final UserProfileService userProfileService;

    @RabbitListener(queues = "queque-create-auth")
    public void createUserConsumerListener(CreateUser createUser) {
        System.out.println("gelen mesaj" + createUser.toString());
        userProfileService.save(UserProfile.builder()
                .authid(createUser.getAuthid())
                .username(createUser.getUsername())
                .mail(createUser.getEmail())
                .build());
    }
}
