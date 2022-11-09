package com.nashtech.ecommerce_website.dto.request;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class AccountCreateRequest {
	
	private String idAccount;
	private String idProfile;
	
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",message = "Email is not valid")
	@NotBlank(message = "Email is required")
	private String email;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$", message = "Must be at least 8 characters, 1 uppercase, 1 lowercase, 1 number, 1 special character")
	@NotBlank(message = "Password is required")
	private String password;
	
	@Pattern(regexp = "^[0-9]+$", message = "Phone is not valid")
	@NotBlank(message = "Phone is required")
	private String phone;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@Min(value = 0, message = "sex must be at least 0")
	@Max(value = 1, message = "sex must be maximum is 1")
	private int sex;
	
	private Date birth;
	
	@NotBlank(message = "address is required")
	private String address;
	
	private String roleId;
	
	private Date createdDate;

	private int isBlocked;
	public AccountCreateRequest(String idAccount, String idProfile, String email, String password, String phone,
			String name, int sex, Date birth, String address, String roleId, Date createdDate,int isBlocked) {
		this.idAccount = idAccount;
		this.idProfile = idProfile;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.name = name;
		this.sex = sex;
		this.birth = birth;
		this.address = address;
		this.roleId = roleId;
		this.createdDate = createdDate;
		this.isBlocked=isBlocked;
	}

	public String getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}

	public String getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(String idProfile) {
		this.idProfile = idProfile;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(int isBlocked) {
		this.isBlocked = isBlocked;
	}
	
	
}
