package com.nashtech.ecommerce_website.dto.request;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ProfileUpdateInfoRequest {
	@NotBlank(message = "id profile is required")
	private String id;
	@NotBlank(message = "accountID is required")
	private String accountId;
	@NotBlank(message = "name is required")
	private String name;
	@Min(value = 0,message = "Sex min is 0")
	@Max(value = 1,message = "Sex max is 1")
	private int sex;
	
//	@NotBlank(message = "birth is required")
	private Date birth;
	@NotBlank(message = "address is required")
	private String address;
	
	
	public ProfileUpdateInfoRequest(String id, String accountId, String name, int sex, Date birth, String address) {
	this.id = id;
	this.accountId = accountId;
	this.name = name;
	this.sex = sex;
	this.birth = birth;
	this.address = address;
}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
