package com.nashtech.ecommerce_website.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RegisterRequest {	
	private String id;
	
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",message = "Email is not valid")
	@NotBlank(message = "Email is required")
	private String email;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$",message = "Must be at least 8 characters, 1 uppercase, 1 lowercase, 1 number, 1 special character")
	@NotBlank(message = "Password is required")
	private String password;
	private String roleId;
	private int isBlocked;
	
	@Pattern(regexp = "^[0-9]+$",message = "Phone is not valid")
	@NotBlank(message = "Phone is required")
	private String phone;
	
	public RegisterRequest() {

	}
	
	public RegisterRequest(String id, String email, String password, String roleId, int isBlocked, String phone) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.roleId = roleId;
		this.isBlocked = isBlocked;
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public int getIsBlocked() {
		return isBlocked;
	}
	public void setIsBlocked(int isBlocked) {
		this.isBlocked = isBlocked;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
