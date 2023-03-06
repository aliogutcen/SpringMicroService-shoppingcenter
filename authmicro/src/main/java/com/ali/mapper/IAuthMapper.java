package com.ali.mapper;

import com.ali.dto.request.AuthRegisterRequestDto;
import com.ali.dto.request.UserProfileRegisterDto;
import com.ali.dto.response.ActivateCodeGeneratorResponseDto;
import com.ali.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);
    Auth toAuthRegister(final AuthRegisterRequestDto dto);
    ActivateCodeGeneratorResponseDto activateCodeGeneratorResponseDto(final Auth auth);
    @Mapping(target = "authid",source = "id")
    UserProfileRegisterDto toUserProfileRegister(final Auth auth);

}
