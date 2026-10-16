package com.tech.gateway.bean;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tech.gateway.enums.UserStates;

import java.util.Date;
import java.util.UUID;

@JsonInclude(value = Include.NON_EMPTY)
public class APIUser extends com.tech.gateway.bean.User {
	private String password;
	private Date createtionDate;
	private com.tech.gateway.enums.UserStates userStates;
	private String acceptTermCondition;
	private String uniqueTxnId;


	public APIUser() {
		super();
	}

	public APIUser(UUID id, String firstName, String lastName, String mobileNumber, String dob, String email,
                   String password, Date createtionDate, com.tech.gateway.enums.UserStates userStates) {
		super(id, firstName, lastName, email, mobileNumber, dob);
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.createtionDate = createtionDate;
		this.userStates = userStates;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatetionDate() {
		return createtionDate;
	}

	public void setCreatetionDate(Date createtionDate) {
		this.createtionDate = createtionDate;
	}

	public com.tech.gateway.enums.UserStates getUserStates() {
		return userStates;
	}

	public void setUserStates(com.tech.gateway.enums.UserStates userStates) {
		this.userStates = userStates;
	}

	public String getAcceptTermCondition() {
		return acceptTermCondition;
	}

	public void setAcceptTermCondition(String acceptTermCondition) {
		this.acceptTermCondition = acceptTermCondition;
	}

	public String getUniqueTxnId() {
		return uniqueTxnId;
	}

	public void setUniqueTxnId(String uniqueTxnId) {
		this.uniqueTxnId = uniqueTxnId;
	}

}
