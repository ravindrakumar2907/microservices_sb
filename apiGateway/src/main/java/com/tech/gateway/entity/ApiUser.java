package com.tech.gateway.entity;


import com.tech.gateway.enums.ApiUserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "api_users")
/*
 * Hibernate
 *
 * @Table(name = "api_users", indexes = {
 *
 * @Index(name = "idx_api_user_email", columnList = "email_address"),
 *
 * @Index(name = "idx_api_user_user_id", columnList = "user_id") })
 */
@Entity
@Setter
@Getter
public class ApiUser {

    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    //@JdbcTypeCode(SqlTypes.BINARY)
    @UuidGenerator
    private UUID apiUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile userProfile;
    // UUID userProfileId;

    //private UUID accountId;

    @Column(name = "email_address", unique = true, nullable = false, length = 255)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
    private String name;
    private com.tech.gateway.enums.ApiUserStatus status;

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;


}
