package com.tech.gateway.bean;

import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.tech.gateway.enums.UserStates;

import jakarta.validation.constraints.NotNull;


@JsonInclude(value = Include.NON_EMPTY)
public class UserRequest {

    private UUID id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private com.tech.gateway.enums.UserStates userStates;

    public UserRequest() {
    }

    public UserRequest(UUID id, String username, String password, UserStates userStates) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.userStates = userStates;
    }

    @Override
    public String toString() {
        return "UserRequest [id=" + id + ", username=" + username + ", password=" + password + ", userStates="
                + userStates + "]";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStates getUserStates() {
        return userStates;
    }

    public void setUserStates(UserStates userStates) {
        this.userStates = userStates;
    }


}
