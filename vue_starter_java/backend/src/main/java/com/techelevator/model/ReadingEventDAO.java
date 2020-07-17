package com.techelevator.model;

import java.util.List;

public interface ReadingEventDAO {
	
	public List<ReadingEvent> getAllReadingEvents();
	
	public ReadingEvent addReadingEvent(ReadingEvent reads);
	public ReadingEvent addChildReadingEvent(ReadingEvent reads, boolean completed);


	ReadingActivity getReadingActivity(Long userId, String userRole);
	

}
