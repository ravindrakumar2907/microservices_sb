package com.tech.gateway.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_verification_tokens")
public class EmailVerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    public EmailVerificationToken() {}

    public EmailVerificationToken(String token, String email, LocalDateTime expiresAt) {
        this.token = token;
        this.email = email;
        this.expiresAt = expiresAt;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public Long getId() { return id; }
    public String getToken() { return token; }
    public String getEmail() { return email; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
}