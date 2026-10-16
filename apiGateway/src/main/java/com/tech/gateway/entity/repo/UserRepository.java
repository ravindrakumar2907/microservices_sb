package com.tech.gateway.entity.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<com.tech.gateway.entity.UserEntity, Long> {

    Optional<com.tech.gateway.entity.UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}