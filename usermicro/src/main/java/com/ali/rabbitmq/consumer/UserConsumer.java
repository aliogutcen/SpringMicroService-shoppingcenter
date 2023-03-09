package com.ali.rabbitmq.consumer;


import com.ali.rabbitmq.model.ActivatedUser;
import com.ali.rabbitmq.model.CreateUser;
import com.ali.repository.IUserProfileRepository;
import com.ali.repository.entity.UserProfile;
import com.ali.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserConsumer {
    private final UserProfileService userProfileService;
    private final IUserProfileRepository userProfileRepository;


    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "queque-create-auth")
    public void createUserConsumerListener(CreateUser createUser) {
        userProfileService.save(createUser);
    }

    @RabbitListener(queues = "queque-activated-auth")
    public void updateUserConsumer(ActivatedUser activatedUser) {
        System.out.println("Gelen user" + activatedUser.toString());
        UserProfile userProfile = userProfileRepository.findOptionalByAuthid(activatedUser.getAuthid());
        userProfile.setEstatus(activatedUser.getEstatus());
        userProfileService.update(userProfile);
    }
}
