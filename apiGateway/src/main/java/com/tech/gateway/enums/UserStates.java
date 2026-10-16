package com.tech.gateway.enums;

public enum UserStates {

	REGISTERED(1, "REGISTERED"),
	ACTIVE(2, "ACTIVE"),
	API_ENABLED(3, "API_ENABLED"),
	API_DISABLED(4, "API_DISABLED"),
	LOCKED(5, "LOCKED"),
	SUSPENDED(6, "SUSPENDED"),
	DELETED(7, "DELETED"),
	INACTIVE(8, "INACTIVE");

	int numCode;
	String status;

	private UserStates(int i, String status) {
		this.numCode = i;
		this.status = status;
	}

	public static UserStates valueOf(int value) {
		UserStates[] types = UserStates.values();
		if (types != null) {
			for (int i = 0; i < types.length; i++) {
				if (types[i].getNumCode() == value)
					return types[i];
			}
		}
		return null;
	}

	public int getNumCode() {
		return numCode;
	}

	public void setNumCode(int numCode) {
		this.numCode = numCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
