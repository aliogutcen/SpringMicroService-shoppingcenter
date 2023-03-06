package com.ali.mapper;

import com.ali.dto.request.UserProfileRegisterDto;
import com.ali.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileManager {

    IUserProfileManager INSTANCE = Mappers.getMapper(IUserProfileManager.class);

    UserProfile toUserProfile(final UserProfileRegisterDto dto);
}
