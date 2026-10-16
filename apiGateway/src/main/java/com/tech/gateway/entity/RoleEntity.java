package com.tech.gateway.entity;


import com.tech.gateway.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false, unique = true)
    private Role roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRoleName() {
        return roleName;
    }

    public void setRoleName(Role roleName) {
        this.roleName = roleName;
    }
}