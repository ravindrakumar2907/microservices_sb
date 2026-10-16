package com.tech.gateway.config;

import java.util.Date;
import java.util.Optional;

import com.api.entity.UserEntity;
import com.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.api.custom.enums.UserStates;
import com.api.dto.APIUser;
import com.api.dto.SessionInfo;
import com.api.service.serviceImple.APIUserServiceImpl;
import com.api.service.serviceImple.SPLocalCache;
import com.api.utils.Utils;

@Component
public class CustomSecurityManager {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final SPLocalCache spLocalCache;

	@Autowired
	public CustomSecurityManager(UserService userService, PasswordEncoder passwordEncoder, SPLocalCache spLocalCache) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
		this.spLocalCache = spLocalCache;
	}

	public boolean authenticate(String username, String passString) {

		UserEntity apiUser = userService.getUserByUsername(username);
		return !ObjectUtils.isEmpty(apiUser)
				&& passwordEncoder.matches(passString, apiUser.getPassword())
				&& apiUser.isEnabled();
	}

	public boolean validateToken(String token) {
		Optional<SessionInfo> sessionInfo = spLocalCache.get(token);
        return sessionInfo.filter(this::isValid).isPresent();
    }

	private boolean isValid(final SessionInfo sessionInfo) {
		return !(new Date().compareTo(sessionInfo.getExpiryDate()) > 0);
	}
}
