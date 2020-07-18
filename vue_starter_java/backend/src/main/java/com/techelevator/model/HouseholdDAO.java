package com.techelevator.model;

import java.math.BigDecimal;

public interface HouseholdDAO {
	
	public int numberOfItemsPerHousehold(long familyId);
	
	public BigDecimal amountSpentPerHousehold(long familyId);
	
	

}
