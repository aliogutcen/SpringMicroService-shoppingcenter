package com.ali.rabbitmq.consumer;

import com.ali.rabbitmq.model.ActivatedUser;
import com.ali.rabbitmq.model.CreateUser;
import com.ali.repository.IUserProfileRepository;
import com.ali.repository.entity.UserProfile;
import com.ali.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserConsumer {
    private final UserProfileService userProfileService;
    private final IUserProfileRepository userProfileRepository;

    @RabbitListener(queues = "queque-create-auth")
    public void createUserConsumerListener(CreateUser createUser) {
        System.out.println("gelen mesaj" + createUser.toString());
        userProfileService.save(UserProfile.builder()
                .authid(createUser.getAuthid())
                .username(createUser.getUsername())
                .mail(createUser.getMail())
                .build());
    }
    @RabbitListener(queues = "queque-activated-auth")
    public void updateUserConsumer(ActivatedUser activatedUser) {
        System.out.println("Gelen user" + activatedUser.toString());
        UserProfile userProfile = userProfileRepository.findOptionalByAuthid(activatedUser.getAuthid());
        userProfile.setEstatus(activatedUser.getEstatus());
        userProfileService.update(userProfile);
    }
}
