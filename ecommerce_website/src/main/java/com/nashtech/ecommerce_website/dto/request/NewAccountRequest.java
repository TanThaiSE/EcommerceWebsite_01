package com.nashtech.ecommerce_website.dto.request;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class NewAccountRequest {
	private String idAccount;

	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Email is not valid")
	@NotBlank(message = "Email is required")
	private String email;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$", message = "Must be at least 8 characters, 1 uppercase, 1 lowercase, 1 number, 1 special character")
	@NotBlank(message = "Password is required")
	private String password;

	@NotBlank(message = "roleId is required")
	private String roleId;

	private int isBlocked;

	@Pattern(regexp = "^[0-9]+$", message = "Phone is not valid")
	@NotBlank(message = "Phone is required")
	private String phone;

	private Date createdDate;
	private String idProfile;
	@NotBlank(message = "Full name is required")
	private String fullName;
	@Min(value = 0, message = "sex must be at least 0")
	@Max(value = 1, message = "sex must be maximum is 1")
	private int sex;

	private Date birth;
	@NotBlank(message = "address is required")
	private String address;

	
	public NewAccountRequest(String idAccount, String email, String password, String roleId, int isBlocked, String phone,
			Date createdDate, String fullName, int sex, Date birth, String address,String idProfile) {
		super();
		this.idAccount = idAccount;
		this.email = email;
		this.password = password;
		this.roleId = roleId;
		this.isBlocked = isBlocked;
		this.phone = phone;
		this.createdDate = createdDate;
		this.fullName = fullName;
		this.sex = sex;
		this.birth = birth;
		this.address = address;
		this.idProfile=idProfile;
	}



	public String getIdProfile() {
		return idProfile;
	}



	public void setIdProfile(String idProfile) {
		this.idProfile = idProfile;
	}



	public String getIdAccount() {
		return idAccount;
	}



	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	@Override
	public String toString() {
		return "NewAccountRequest [idAccount=" + idAccount + ", email=" + email + ", password=" + password + ", roleId="
				+ roleId + ", isBlocked=" + isBlocked + ", phone=" + phone + ", createdDate=" + createdDate
				+ ", fullName=" + fullName + ", sex=" + sex + ", birth=" + birth + ", address=" + address + "]";
	}

}
