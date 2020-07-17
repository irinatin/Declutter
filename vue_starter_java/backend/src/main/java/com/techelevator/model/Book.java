package com.techelevator.model;

import org.hibernate.validator.constraints.NotBlank;

public class Book {
	
	@NotBlank(message = "Username is required")
    private String title;
	
	@NotBlank(message = "Author is required")
    private String author;
	
	private long isbn;
	
	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	private long id;
	
	private long family_id;
	
	public long getFamily_id() {
		return family_id;
	}

	public void setFamily_id(long family_id) {
		this.family_id = family_id;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	

	
}
