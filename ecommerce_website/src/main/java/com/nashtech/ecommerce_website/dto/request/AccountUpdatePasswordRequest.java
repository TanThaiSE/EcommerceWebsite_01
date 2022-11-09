package com.nashtech.ecommerce_website.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class AccountUpdatePasswordRequest {
	@NotBlank(message = "accountId is required")
	private String id;
	@NotBlank(message="oldPassword is required")
	private String oldPassword;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$", message = "Must be at least 8 characters, 1 uppercase, 1 lowercase, 1 number, 1 special character")
	@NotBlank(message = "New password is required")
	private String newPassword;

	
	
	public AccountUpdatePasswordRequest(String id, String oldPassword, String newPassword) {
	this.id = id;
	this.oldPassword = oldPassword;
	this.newPassword = newPassword;
}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	


	
}
