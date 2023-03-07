package com.ali.mapper;

import com.ali.dto.request.UpdateAuthRequestDto;
import com.ali.dto.request.UserProfileRegisterDto;
import com.ali.dto.request.UserProfileUpdateDto;
import com.ali.dto.response.UpdateUserProfileResponseDto;
import com.ali.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {

    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

    UserProfile toUserProfile(final UserProfileRegisterDto dto);

    UserProfile toUserProfileUpdate(final UserProfileUpdateDto dto);

    UpdateUserProfileResponseDto toUpdateUserProfileResponseDto(final UserProfile userProfile);
    UpdateAuthRequestDto toUpdateAuthRequestDto(final UserProfile userProfile);
}
