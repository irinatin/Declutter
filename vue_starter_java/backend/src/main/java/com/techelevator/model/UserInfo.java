package com.techelevator.model;

public class UserInfo {
	
	private long userInfoId;
	
	private long userId;
	
	private String firstName;
	
	private String lastName;
	
	private long familyId;
	
	private String familyName;
	
	private boolean ifNewFamily;

	public long getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(long userInfoId) {
		this.userInfoId = userInfoId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public long getFamilyId() {
		return familyId;
	}

	public void setFamilyId(long familyId) {
		this.familyId = familyId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public boolean isIfNewFamily() {
		return ifNewFamily;
	}

	public void setIfNewFamily(boolean ifNewFamily) {
		this.ifNewFamily = ifNewFamily;
	}
	
	

}
