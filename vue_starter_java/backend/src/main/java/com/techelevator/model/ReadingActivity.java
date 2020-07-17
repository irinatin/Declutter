package com.techelevator.model;

import java.util.List;
import java.util.Map;

public class ReadingActivity {
	
	private int completedBooks;
	
	private int totalReadingTime;
	
	private List<String> currentBooks;
	
	private Map<String, Integer> progressTowardsPrize;
	
	private String title;
	
	private int readingTime;
	

	public String getTitle() {
		return title;
	}

	public int getCompletedBooks() {
		return completedBooks;
	}

	public void setCompletedBooks(int completedBooks) {
		this.completedBooks = completedBooks;
	}

	public int getTotalReadingTime() {
		return totalReadingTime;
	}

	public void setTotalReadingTime(int totalReadingTime) {
		this.totalReadingTime = totalReadingTime;
	}

	public List<String> getCurrentBooks() {
		return currentBooks;
	}

	public void setCurrentBooks(List<String> currentBooks) {
		this.currentBooks = currentBooks;
	}

	public Map<String, Integer> getProgressTowardsPrize() {
		return progressTowardsPrize;
	}

	public void setProgressTowardsPrize(Map<String, Integer> progressTowardsPrize) {
		this.progressTowardsPrize = progressTowardsPrize;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getReadingTime() {
		return readingTime;
	}

	public void setReadingTime(int readingTime) {
		readingTime = readingTime;
	}
	
	

}
