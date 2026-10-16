package com.tech.gateway.bean;

import java.util.Date;

/**
 * This class represent the user cache for token based API login
 */
public class SessionInfo {

	private String token;
	private String username;
	private Date creationDate;
	private Date expiryDate;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}

//https://www.viralpatel.net/java-create-validate-jwt-token/
//https://metamug.com/article/security/jwt-java-tutorial-create-verify.html
