package com.tech.gateway.entity.repo;

import com.tech.gateway.entity.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApiUserRepositoryJPA extends JpaRepository<ApiUser, UUID> {

}
