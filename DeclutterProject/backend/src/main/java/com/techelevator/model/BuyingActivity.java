package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

public class BuyingActivity {
	
	private int itemsBought;
	
	private BigDecimal totalMoneySpent;
	
	private List <String> currentItems;

	public int getItemsBought() {
		return itemsBought;
	}

	public void setItemsBought(int itemsBought) {
		this.itemsBought = itemsBought;
	}

	public BigDecimal getTotalMoneySpent() {
		return totalMoneySpent;
	}

	public void setTotalMoneySpent(BigDecimal totalMoneySpent) {
		this.totalMoneySpent = totalMoneySpent;
	}

	public List<String> getCurrentItems() {
		return currentItems;
	}

	public void setCurrentItems(List<String> currentItems) {
		this.currentItems = currentItems;
	}

}
