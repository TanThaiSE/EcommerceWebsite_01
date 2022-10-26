package com.nashtech.ecommerce_website.entity;


import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity(name="account")
public class Accounts {
	@Id
	private String id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Roles roles;
	
	@Column(name="is_blocked")
	private int isBlocked;

	@OneToMany(mappedBy = "accountCart")
	private Set<Carts> carts;
	
	@OneToMany(mappedBy = "accountsComments")
	private Set<Comments> comments;
	
	@OneToOne(mappedBy = "accountsProfiles")
	private Profiles profiles;
	
//	@OneToMany(mappedBy = "accountOrderDetails")
//	private Set<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy = "accountOrder")
	private Set<Orders> orders;
	
	@Column(name = "phone")
	private String phone;
	
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

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
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
