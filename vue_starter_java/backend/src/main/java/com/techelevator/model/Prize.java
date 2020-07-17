package com.techelevator.model;

import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class Prize {
	
	private long prizeId;
	
	@NotBlank(message = "Prize name is required")
	private String prizeName;
	
	@NotBlank(message = "Prize description is required")
	private String prizeDescription;
	
	@NotBlank(message = "Milestone is required")
	@Min(value = 1, message = "Please enter value greater than or equal to 1")
	private int milestone;
	
	@NotBlank(message = "User group is required")
	private String userGroup;
	
	@NotBlank(message = "Number of prizes is required")
	@Min(value = 1, message = "Please enter value greater than or equal to 1")
	private int numOfPrizes;
	
	@NotBlank(message = "Start date is required")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date startDate;
	
	@NotBlank(message = "End date is required")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date endDate;
	
	

	public long getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(long prizeId) {
		this.prizeId = prizeId;
	}

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

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public int getNumOfPrizes() {
		return numOfPrizes;
	}

	public void setNumOfPrizes(int numOfPrizes) {
		this.numOfPrizes = numOfPrizes;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
