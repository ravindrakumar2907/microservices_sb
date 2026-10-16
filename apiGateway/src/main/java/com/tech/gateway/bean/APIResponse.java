package com.tech.gateway.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

@JsonInclude(value = Include.NON_EMPTY)
public class APIResponse {

	private String txnId;
	private String timestamp;
	private String status;
	private String result;
	private String error;
	private String errorDescription;
	private Map<String, Object> response;
	private JsonNode responseInfo;
	private List<APIUser> list;

	public APIResponse() {

	}

	public APIResponse(String txnId, String timestamp, String status) {
		this.txnId = txnId;
		this.timestamp = timestamp;
		this.status = status;
	}

	public APIResponse(String txnId, String timestamp, String status, String error, String errorDescription) {
		this.txnId = txnId;
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.errorDescription = errorDescription;

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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Map<String, Object> getResponse() {
		return response;
	}

	public void setResponse(Map<String, Object> response) {
		this.response = response;
	}

	public List<APIUser> getList() {
		return list;
	}

	public void setList(List<APIUser> listOfUser) {
		this.list = listOfUser;
	}

	public JsonNode getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(JsonNode responseInfo) {
		this.responseInfo = responseInfo;
	}

	@Override
	public String toString() {
		return "APIResponse [txnId=" + txnId + ", timestamp=" + timestamp + ", status=" + status + ", result=" + result
				+ ", error=" + error + ", errorDescription=" + errorDescription + ", response=" + response
				+ ", responseInfo=" + responseInfo + ", list=" + list + "]";
	}
	
	

}
