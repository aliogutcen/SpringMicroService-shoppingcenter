package com.ali.dto.request;

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
}
