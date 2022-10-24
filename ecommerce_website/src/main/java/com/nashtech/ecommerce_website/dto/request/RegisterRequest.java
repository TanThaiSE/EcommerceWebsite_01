package com.nashtech.ecommerce_website.dto.request;

public class RegisterRequest {	
	private String id;
	private String email;
	private String password;
	private String roleId;
	private int isBlocked;
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
