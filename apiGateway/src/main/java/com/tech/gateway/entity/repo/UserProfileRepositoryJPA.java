package com.tech.gateway.entity.repo;

import com.tech.gateway.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepositoryJPA extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByUserId(Long userId);

    Optional<UserProfile> findByEmail(String email);
}