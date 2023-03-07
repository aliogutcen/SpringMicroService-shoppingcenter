package com.ali.service;

import com.ali.dto.request.UserProfileRegisterDto;
import com.ali.dto.request.UserProfileUpdateDto;
import com.ali.dto.response.UpdateUserProfileResponseDto;
import com.ali.exception.ErrorType;
import com.ali.exception.UserMicroServiceException;
import com.ali.manager.IAuthManager;
import com.ali.mapper.IUserProfileMapper;
import com.ali.repository.IUserProfileRepository;
import com.ali.repository.entity.UserProfile;
import com.ali.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile, Long> {

    private final IAuthManager authManager;
    private final IUserProfileRepository userProfileRepository;

    public UserProfileService(IUserProfileRepository userProfileRepository, IAuthManager authManager) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
        this.authManager = authManager;
    }

    public Boolean registerUserProfile(UserProfileRegisterDto userProfileRegisterDto) {
        save(IUserProfileMapper.INSTANCE.toUserProfile(userProfileRegisterDto));
        return true;
    }

    public UpdateUserProfileResponseDto updateUserProfile(UserProfileUpdateDto userProfileUpdateDto) {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findOptionalByUsernameOrMail(userProfileUpdateDto.getUsername(), userProfileUpdateDto.getMail());
        if (optionalUserProfile.isPresent()) throw new UserMicroServiceException(ErrorType.USERNAME_OR_EMAIL_DUPLICATE);
        UserProfile userProfile = userProfileRepository.findOptionalByAuthid(userProfileUpdateDto.getAuthid());
        userProfile.setAge(userProfileUpdateDto.getAge());
        userProfile.setAbout(userProfileUpdateDto.getAbout());
        userProfile.setUsername(userProfileUpdateDto.getUsername());
        update(userProfile);
        authManager.updateAuth(IUserProfileMapper.INSTANCE.toUpdateAuthRequestDto(userProfile));
        return IUserProfileMapper.INSTANCE.toUpdateUserProfileResponseDto(userProfile);

    }
}
