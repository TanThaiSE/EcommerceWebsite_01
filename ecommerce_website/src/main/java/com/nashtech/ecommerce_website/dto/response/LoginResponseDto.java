package com.nashtech.ecommerce_website.dto.response;

public class LoginResponseDto {
	private String id;
	private String userName;
	private String idRole;
	private String nameRole;
	private String token;

	
	public LoginResponseDto(String id, String userName, String idRole, String nameRole, String token) {
		this.id = id;
		this.userName = userName;
		this.idRole = idRole;
		this.nameRole = nameRole;
		this.token = token;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdRole() {
		return idRole;
	}
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}
	public String getNameRole() {
		return nameRole;
	}
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	
}
