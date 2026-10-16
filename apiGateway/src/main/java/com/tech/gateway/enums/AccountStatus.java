package com.tech.gateway.enums;

public enum AccountStatus {
	PENDING_VERIFICATION(101, "PENDING_VERIFICATION"), 
	ACTIVE(102, "ACTIVE"), 
	SUSPENDED(103, "SUSPENDED"),
	DISABLED(104, "DISABLED"), 
	DELETED(105, "DELETED");

	int code;
	String status;

	private AccountStatus(int i, String status) {
		this.code = i;
		this.status = status;
	}

	public static AccountStatus valueOf(int value) {
		AccountStatus[] types = AccountStatus.values();
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
