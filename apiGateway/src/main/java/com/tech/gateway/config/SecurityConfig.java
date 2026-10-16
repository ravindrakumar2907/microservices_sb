package com.tech.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                // H2 Console Support (allow frames from same origin)
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

                .authorizeHttpRequests(auth -> auth
                        // --- API routes (token endpoint, versioned APIs) ---
                        .requestMatchers(
                                "/api/v1/**",
                                "/api/auth/token",
                                "/h2-console/**",
                                "/h2-console"
                        ).permitAll()

                        // --- Public Pages ---
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
                                "/reset-password",
                                "/resetPassword",
                                "/submitResetPassword",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/scalar.html",
                                "/api-docs",
                                "/api-docs/**",
                                "/actuator/**"
                        ).permitAll()

                        // --- Admin Pages ---
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // --- Everything else requires authentication ---
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}