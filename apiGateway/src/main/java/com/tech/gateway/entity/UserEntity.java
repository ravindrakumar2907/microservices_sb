package com.tech.gateway.entity;

import com.tech.gateway.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "enabled")
    private boolean enabled = false;

    @Column(name = "email_verified")
    private boolean emailVerified = false;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    

    @Column(name = "accept_term_condition", nullable = false)
	private String acceptTermCondition;

	@UpdateTimestamp
	@Column(name = "accept_term_condition_date", nullable = false)
	private LocalDateTime acceptTermConditionDate;

    // --- OLD APPROACH (Keeping for reference) ---
    // @ManyToMany(fetch = FetchType.EAGER)
    // @JoinTable(
    //         name = "user_roles",
    //         joinColumns = @JoinColumn(name = "user_id"),
    //         inverseJoinColumns = @JoinColumn(name = "role_id")
    // )
    // private Set<RoleEntity> roles = new HashSet<>();
    
    // --- NEW APPROACH (Simplified for Dev) ---
	
	
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role_name", nullable = false)
    @Enumerated(EnumType.STRING)
    //@org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private Set<Role> roles = new HashSet<>();
    
    
    

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        acceptTermConditionDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    
}