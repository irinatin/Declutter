package com.techelevator.model;

import java.time.LocalDate;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReadingEvent {
	
	private long readingEventId;
	
	@NotBlank(message = "User is required")
	private long userId;
	
	@NotBlank(message = "Book ID is required")
	private int bookId;
	
	@NotBlank(message = "Reading time is required")
	@Min(value = 1, message = "Please enter valid time in minutes")
	private int readingTime;
	
	@NotBlank(message = "Date is required")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")  
	private String readingDate;
	
	@NotBlank(message = "Format is required")
	private String format;
	
	private String notes;
	
	private boolean completed;
	
	private String bookTitle;

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public long getReadingEventId() {
		return readingEventId;
	}

	public void setReadingEventId(long readingEventId) {
		this.readingEventId = readingEventId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getReadingTime() {
		return readingTime;
	}

	public void setReadingTime(int readingTime) {
		this.readingTime = readingTime;
	}

	public String getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(String readingDate) {
		this.readingDate = readingDate;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	

}
