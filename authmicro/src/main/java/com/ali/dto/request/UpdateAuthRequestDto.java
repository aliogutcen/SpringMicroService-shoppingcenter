package com.ali.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateAuthRequestDto {

    Long authid;

    String username;
    String mail;
}
