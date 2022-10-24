package com.nashtech.ecommerce_website.dto.request;

import java.util.Date;

public class ProfileRequest {
	private String id;
	private String accountId;
	private String name;
	private int sex;
	private Date birth;
	private String address;
	
	public ProfileRequest() {

	}
	public ProfileRequest(String id, String accountId, String name, int sex, Date birth, String address) {
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
