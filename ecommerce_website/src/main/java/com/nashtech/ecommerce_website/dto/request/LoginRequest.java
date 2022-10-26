package com.nashtech.ecommerce_website.dto.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank(message="userName is required")
	private String userName;
	@NotBlank(message ="password is required")
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
