package com.techelevator.model;

import java.util.List;

public interface BuyingEventDAO {
	
	public List<BuyingEvent> getAllBuyingEvents();
	
	public BuyingEvent addBuyingEvent(BuyingEvent buyingEvent);
	
	BuyingActivity getBuyingActivity(long userId);

}
