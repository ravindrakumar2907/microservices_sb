package com.tech.gateway.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.tech.gateway.bean.SessionInfo;
import com.tech.gateway.bean.TokenResponse;
import com.tech.gateway.bean.UserRequest;
import com.tech.gateway.cache.SPLocalCache;
import com.tech.gateway.config.CustomSecurityManager;
import com.tech.gateway.constant.Constant;
import com.tech.gateway.utils.DateUtils;
import com.tech.gateway.utils.Utils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = com.tech.gateway.controller.APIVersionPath.API_BASE_V)
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	private final CustomSecurityManager customSecurityManager;

	private final SPLocalCache localCache;
	
	@Autowired
	public AuthController(CustomSecurityManager customSecurityManager, SPLocalCache localCache) {
		this.customSecurityManager = customSecurityManager;
		this.localCache = localCache;
	}



	@PostMapping(value = "/auth/token", consumes = "application/json", produces = "application/json")
	public ResponseEntity<TokenResponse> token(@RequestBody UserRequest userRequest) {
		TokenResponse tokenResponse = new TokenResponse(UUID.randomUUID().toString(), DateUtils.getDateInString(), Constant.ERROR);
		tokenResponse.setError(HttpStatus.UNAUTHORIZED.name());
		tokenResponse.setErrorDescription(Constant.USERNAME_OR_PASSWORD_NOT_VALID);

		logger.debug("Token Request {} ", userRequest);
		if (ObjectUtils.isEmpty(tokenResponse)) {
			logger.error("Token Request is empty.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(tokenResponse);
		}

		String username = userRequest.getUsername().trim();
		String password = userRequest.getPassword().trim();

		if (org.apache.commons.lang3.StringUtils.isBlank(username) || org.apache.commons.lang3.StringUtils.isBlank(password)) {
			logger.error("User is UNAUTHORIZED");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(tokenResponse);
		}

		if (!customSecurityManager.authenticate(username, password)) {
			logger.error("User is UNAUTHORIZED");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(tokenResponse);
		}

		Date creationDate = new Date();
		String token;
		try {
			token = Utils.getSecureKey();
			if (token == null) {
				token = RandomStringUtils.randomAlphanumeric(50);
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error("Error generating secure key, falling back to random token", e);
			token = RandomStringUtils.randomAlphanumeric(50);
		}
		
		int tokenExpiryHours = 24;
		Date tokenExpiryDate = DateUtils.addHoursToJavaUtilDate(creationDate, tokenExpiryHours);
		
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo.setToken(token);
		sessionInfo.setCreationDate(creationDate);
		sessionInfo.setExpiryDate(tokenExpiryDate);
		sessionInfo.setUsername(username);
		localCache.save(sessionInfo);
		
		tokenResponse.setError(null);
		tokenResponse.setErrorDescription(null);
		tokenResponse.setTokenType("Bearer");
		tokenResponse.setToken(token);
		tokenResponse.setStatus(Constant.SUCCESS);
		tokenResponse.setExpiryTime(TimeUnit.MILLISECONDS.toSeconds(tokenExpiryDate.getTime()));
		return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);

	}
}
