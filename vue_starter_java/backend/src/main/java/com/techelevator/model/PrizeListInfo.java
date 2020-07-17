package com.techelevator.model;

public class PrizeListInfo {

	private String prizeName;
	private String userGroup;
	private int milestone;
	private int readingTime;
	private long Id;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	public String getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	public int getMilestone() {
		return milestone;
	}
	public void setMilestone(int milestone) {
		this.milestone = milestone;
	}
	public int getReadingTime() {
		return readingTime;
	}
	public void setReadingTime(int readingTime) {
		this.readingTime = readingTime;
	}
	
	
	
}
