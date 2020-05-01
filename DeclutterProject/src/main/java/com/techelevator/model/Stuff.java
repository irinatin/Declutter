package com.techelevator.model;

import java.math.BigDecimal;
import java.util.Date;

public class Stuff {
	
	private long stuffId;
	
	private long categoryId;
	
	private String item;
	
	private int quantity;
	
	private BigDecimal pricePerItem = new BigDecimal(0);
	
	private Date dateBought;

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

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPricePerItem() {
		return pricePerItem;
	}

	public void setPricePerItem(BigDecimal pricePerItem) {
		this.pricePerItem = pricePerItem;
	}

	public Date getDateBought() {
		return dateBought;
	}

	public void setDateBought(Date dateBought) {
		this.dateBought = dateBought;
	}

}
