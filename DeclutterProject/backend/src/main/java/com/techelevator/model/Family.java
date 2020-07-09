package com.techelevator.model;

import org.hibernate.validator.constraints.NotBlank;

public class Family {
	
	private long familyId;
	
	@NotBlank(message = "Family name is required")
	private String familyName;

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

}
