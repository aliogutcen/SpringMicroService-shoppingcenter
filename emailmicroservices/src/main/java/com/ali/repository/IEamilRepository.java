package com.ali.repository;

import com.ali.repository.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEamilRepository extends JpaRepository<Email,Long> {
}
