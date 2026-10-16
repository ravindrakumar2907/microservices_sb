package com.tech.gateway.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



@JsonInclude(value = Include.NON_EMPTY)
public class TokenResponse {


	private Long id;
	private String txnId;
	private String timestamp;
	private String status;
	private String token;

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Long getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(Long expiryTime) {
		this.expiryTime = expiryTime;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	private String tokenType;
	private String error;
	private Long expiryTime = null; // Expire time 24 hours
	private String errorDescription;

	public TokenResponse() {
	}

	public TokenResponse(String txnId, String timestamp, String status) {
		this.txnId = txnId;
		this.timestamp = timestamp;
		this.status = status;
	}

	@Override
	public String toString() {
		return "TokenResponse [txnId=" + txnId + ", timestamp=" + timestamp + ", status=" + status + ", token=" + token
				+ ", tokenType=" + tokenType + ", error=" + error + ", expiryTime=" + expiryTime + ", errorDescription="
				+ errorDescription + "]";
	}

}
