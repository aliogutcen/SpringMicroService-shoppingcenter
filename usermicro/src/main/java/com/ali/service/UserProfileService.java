package com.ali.service;

import com.ali.dto.request.UserProfileRegisterDto;
import com.ali.mapper.IUserProfileManager;
import com.ali.repository.IUserProfileRepository;
import com.ali.repository.entity.UserProfile;
import com.ali.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile, Long> {

    private final IUserProfileRepository userProfileRepository;

    public UserProfileService(IUserProfileRepository userProfileRepository) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
    }

    public Boolean registerUserProfile(UserProfileRegisterDto dto) {
        save(IUserProfileManager.INSTANCE.toUserProfile(dto));
        return true;
    }
}
