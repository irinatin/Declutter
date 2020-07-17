package com.techelevator.model;

import java.time.LocalDate;
import java.util.Date;

public class AwardedPrize {
	
	private String prizeName;
	private String prizeDescription;
	private int milestone;
	private Date awardDate;
	
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	public String getPrizeDescription() {
		return prizeDescription;
	}
	public void setPrizeDescription(String prizeDescription) {
		this.prizeDescription = prizeDescription;
	}
	public int getMilestone() {
		return milestone;
	}
	public void setMilestone(int milestone) {
		this.milestone = milestone;
	}
	public Date getAwardDate() {
		return awardDate;
	}
	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}
	
	

}
