package com.ali.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUser implements Serializable {

    Long authid;

    String username;

    String email;
}
