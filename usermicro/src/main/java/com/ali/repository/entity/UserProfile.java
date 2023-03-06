package com.ali.repository.entity;

import com.ali.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class UserProfile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long authid;

    @Column(unique = true)
    String username;
    @Column(unique = true)
    String mail;
    String age;

    String about;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    EStatus estatus = EStatus.PENDING;

}
