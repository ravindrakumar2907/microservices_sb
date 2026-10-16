package com.tech.gateway.bean;

import java.util.UUID;

import com.api.custom.enums.UserStates;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(value = Include.NON_EMPTY)
@Getter
@Setter
public class UserRequest {

	@Hidden
	private UUID id;
	
	@NotNull
	@Schema(description = "Username", example = "demo@gmail.com")
	private String username;
	
	@NotNull
	@Schema(description = "Password", example = "demo@123456")
	private String password;

	@Hidden
	private UserStates userStates;

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
