//package com.nashtech.ecommerce_website.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//@Entity(name = "users")
//public class Users {
//
//	@Id
//	private String id;
//	
//	@Column(name="username")
//	private String userName;
//	
//	@Column(name="password")
//	private String password;
//	@ManyToOne
//	@JoinColumn(name = "role_id")
//	private Roles role;
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public Roles getRole() {
//		return role;
//	}
//
//	public void setRole(Roles role) {
//		this.role = role;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	
//	
//}
