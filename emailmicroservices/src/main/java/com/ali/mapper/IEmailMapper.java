package com.ali.mapper;

import com.ali.dto.request.EmailSaveUserProfile;
import com.ali.rabbitmq.model.MailCreate;
import com.ali.repository.entity.Email;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEmailMapper {

    IEmailMapper INSTANCE = Mappers.getMapper(IEmailMapper.class);


    Email toEmail(final EmailSaveUserProfile emailSaveUserProfile);

    Email toEmail(final MailCreate mailCreate);
}
