package com.nashtech.ecommerce_website.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="profile")
public class Profiles {
	@Id
	private String id;
	
	@OneToOne
	@JoinColumn(name="account_id")
	private Accounts accountsProfiles;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "sex")
	private int sex;
	
	@Column(name = "birth")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date birth;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Accounts getAccountsProfiles() {
		return accountsProfiles;
	}

	public void setAccountsProfiles(Accounts accountsProfiles) {
		this.accountsProfiles = accountsProfiles;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
