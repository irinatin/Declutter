package com.techelevator.model;

public class Stuff {
	
	private long itemId;
	
	private long categoryId;
	
	private String item;
	
	private long familyId;
	
	
	
	public long getItemId() {
		return itemId;
	}

	public long getFamilyId() {
		return familyId;
	}

	public void setFamilyId(long familyId) {
		this.familyId = familyId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	

}
