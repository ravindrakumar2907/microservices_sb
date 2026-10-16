package com.tech.gateway.enums;

public enum ApiUserStatus {
	ACTIVE(1, "ACTIVE"), SUSPENDED(2, "SUSPENDED"), DISABLED(3, "DISABLED"), DELETED(4, "DELETED");

	int code;
	String status;

	private ApiUserStatus(int i, String status) {
		this.code = i;
		this.status = status;
	}

	public static ApiUserStatus valueOf(int value) {
		ApiUserStatus[] types = ApiUserStatus.values();
		if (types != null) {
			for (int i = 0; i < types.length; i++) {
				if (types[i].getCode() == value)
					return types[i];
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
