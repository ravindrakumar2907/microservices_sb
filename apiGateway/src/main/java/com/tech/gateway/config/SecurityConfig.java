package com.tech.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                // H2 Console Support (allow frames from same origin)
                .headers(headers -> headers
                        .frameOptions(frame -> frame.mode(
                                org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter.Mode.SAMEORIGIN)))

                .authorizeExchange(exchanges -> exchanges
                        // --- API routes (token endpoint, versioned APIs) ---
                        .pathMatchers(
                                "/api/v1/**",
                                "/api/auth/token"
                        ).permitAll()

                        // --- Public Pages ---
                        .pathMatchers(
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
                                "/reset-password",
                                "/resetPassword",
                                "/submitResetPassword"
                        ).permitAll()

                        // --- Static resources ---
                        .pathMatchers(
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // --- H2 Console ---
                        .pathMatchers("/h2-console/**").permitAll()

                        // --- API docs & Actuator ---
                        .pathMatchers(
                                "/scalar.html",
                                "/api-docs",
                                "/api-docs/**",
                                "/actuator/**"
                        ).permitAll()

                        // --- Admin Pages ---
                        .pathMatchers("/admin/**").hasRole("ADMIN")

                        // --- Everything else requires authentication ---
                        .anyExchange().authenticated()
                );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}