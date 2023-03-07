package com.ali.dto.request;

import com.ali.repository.enums.ERole;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfileRegisterDto {

    Long authid;


    String username;

    String mail;

    ERole erole;

}
