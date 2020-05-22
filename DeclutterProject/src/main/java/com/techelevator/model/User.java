package com.techelevator.model;

import javax.validation.constraints.AssertTrue;

import org.hibernate.validator.constraints.NotBlank;

public class User {
	
	private long userId;
	
	@NotBlank(message = "Username is required")
	private String username;
	
	@NotBlank(message = "Password is required")
	private String password;
	
	private String confirmPassword;
	
//	@NotBlank(message= "Role is required")
//	private String role;
	
	@AssertTrue(message = "Passwords must match")
    private boolean isPasswordMatching() {
        if (password != null) {
            return password.equals(confirmPassword);
        }
        return true;
    }

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}

	
	
	
	
	
	
	
	
	
	

}
