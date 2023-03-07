package com.ali.repository;

import com.ali.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findOptionalByUsernameOrMail(String username, String mail);

    UserProfile findOptionalByAuthid(Long authid);
}
