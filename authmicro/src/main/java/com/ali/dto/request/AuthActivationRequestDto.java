package com.ali.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthActivationRequestDto {

    Long id;
    String activateCode;
}
