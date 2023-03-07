package com.ali.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateUserProfileResponseDto {

    String username;

    String mail;

    String age;

    String about;


}
