package com.tech.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final UserDetailsService userDetailsService;

	public SecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	@Order(1)
	SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/api/v1/**","/api/auth/token")
				.csrf(csrf -> csrf.disable());
		return http.build();
	}

	@Bean
	@Order(2)
	SecurityFilterChain uiSecurityFilterChain(HttpSecurity http) throws Exception {

		http
				// H2 Console Support
				.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

				.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))

				.authorizeHttpRequests(auth -> auth
						// Public Pages
						.requestMatchers(
								"/",
								"/login",
								"/contact",
								"/signUp",
								"/error",
								"/about",
								"/documentation",
								"/apiServices",
								"/services",
								"/faq",
								"/links",
								"/termsAndConditions",
								"/privacyAndPolicy",
								"/moreAboutAPI",
								"/distanceDemo",
								"/allAPIDemo",
								"/freeServices",
								"/freeHostings",
								"/verify-email",
								"/verifyEmailAddress",
								"/resend-verification",
								"/forgot-password",
								"/forgotPasswordRequest",
								"/submitForgotPasswordRequest",
								"/msgDisplay",
								"/error",
								"/reset-password",
								"/resetPassword",
								"/submitResetPassword",
								"/css/**",
								"/js/**",
								"/images/**",
								"/h2-console/**",
								"/scalar.html",
								"/api-docs",
								"/actuator/**",
								"/api-docs/**")
						.permitAll()

						// Admin Pages
						.requestMatchers("/admin/**")
						.hasRole("ADMIN")

						// Everything else requires login
						.anyRequest()
						.authenticated());


		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
}