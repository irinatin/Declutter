package com.techelevator.model;


//This is a joiner table for stuff and category
public class Household {
	
	private long householdId;
	
	private long stuffId;
	
	private long categoryId;

	public long getHouseholdId() {
		return householdId;
	}

	public void setHouseholdId(long householdId) {
		this.householdId = householdId;
	}

	public long getStuffId() {
		return stuffId;
	}

	public void setStuffId(long stuffId) {
		this.stuffId = stuffId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	
	

}
