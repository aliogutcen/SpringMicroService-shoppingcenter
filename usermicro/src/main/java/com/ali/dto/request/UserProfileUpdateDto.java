package com.ali.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfileUpdateDto {


    Long authid;

    String username;

    String mail;

    String age;

    String about;

}
