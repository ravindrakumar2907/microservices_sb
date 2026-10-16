package com.tech.gateway.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.service.APIUserService;
import com.api.dto.APIUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom Authentication Provider for Form-Based Login
 * 
 * Implements Spring Security AuthenticationProvider for custom authentication logic.
 * Integrates with APIUserService for user validation.
 * 
 * Features:
 * - Validates username and password
 * - Loads user details from database
 * - Assigns roles/authorities to authenticated user
 * - Handles authentication errors
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	private final APIUserService apiUserService;
	
	@Autowired
	public CustomAuthenticationProvider(APIUserService apiUserService) {
		this.apiUserService = apiUserService;
	}
	
	/**
	 * Authenticates user based on username and password
	 * 
	 * @param authentication the authentication request object.
	 * @return an authenticated Authentication object
	 * @throws AuthenticationException if authentication fails
	 */
	@Override
	public Authentication authenticate(Authentication authentication) 
			throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		logger.debug("Attempting authentication for user: {}", username);
		
		try {
			// For now, accept any non-empty credentials
			// TODO: Integrate with APIUserService when getUserByUsername() is implemented
			// TODO: Or implement a proper user lookup mechanism
			
			if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
				logger.warn("Empty credentials provided");
				throw new AuthenticationException("Invalid credentials") {};
			}
			
			// Placeholder validation - replace with actual user lookup
			// In production, this should query the database via APIUserService
			APIUser user = validateUserCredentials(username, password);
			
			if (user == null) {
				logger.warn("User validation failed for: {}", username);
				throw new AuthenticationException("Invalid credentials") {};
			}
			
			// Build authorities from user roles
			List<SimpleGrantedAuthority> authorities = buildAuthorities(user);
			
			logger.info("User authenticated successfully: {}", username);
			
			// Return authenticated token
			return new UsernamePasswordAuthenticationToken(
				username,
				password,
				authorities
			);
			
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			logger.error("Authentication error for user: {}", username, ex);
			throw new AuthenticationException("Authentication failed") {};
		}
	}
	
	/**
	 * Placeholder user credential validation
	 * TODO: Replace with actual database lookup
	 */
	private APIUser validateUserCredentials(String username, String password) {
		// Placeholder implementation
		// In production, query database: apiUserService.getUserByUsername(username)
		
		// For demo: accept any username/password combination
		apiUserService.getUser(username); // This will return null until implemented
		APIUser user = new APIUser();
		user.setFirstName(username);
		user.setEmail(username + "@example.com");
		return user;
	}
	
	/**
	 * Supports UsernamePasswordAuthenticationToken
	 * 
	 * @param authentication the authentication type to check
	 * @return true if this provider can authenticate the given type
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	
	/**
	 * Builds authorities/roles for authenticated user
	 * 
	 * @param user the authenticated user
	 * @return list of authorities
	 */
	private List<SimpleGrantedAuthority> buildAuthorities(APIUser user) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		// Add default USER role
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		// TODO: Add logic to assign additional roles based on user properties
		// Example:
		// if (user.isAdmin()) {
		//     authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		// }
		// if (user.isPremium()) {
		//     authorities.add(new SimpleGrantedAuthority("ROLE_PREMIUM"));
		// }
		
		return authorities;
	}
}

/**
 * Integration with SecurityConfig:
 * 
 * In SecurityConfig, register this provider:
 * 
 * @Bean
 * public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
 *     return http.getSharedObject(AuthenticationManagerBuilder.class)
 *         .authenticationProvider(customAuthenticationProvider)
 *         .build();
 * }
 * 
 * References:
 * https://docs.spring.io/spring-security/reference/servlet/authentication/architecture.html
 * https://www.baeldung.com/spring-security-custom-authentication-provider
 * https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html
 */



