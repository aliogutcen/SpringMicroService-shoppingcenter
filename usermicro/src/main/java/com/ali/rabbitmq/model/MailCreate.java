package com.ali.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MailCreate implements Serializable {

    Long userid;

    Long authid;

    String mail;
}
