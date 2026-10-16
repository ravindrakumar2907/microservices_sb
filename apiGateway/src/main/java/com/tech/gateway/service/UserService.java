package com.tech.gateway.service;

import com.tech.gateway.entity.UserEntity;
import com.tech.gateway.entity.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByEmail(username).orElse(null);
    }
}
