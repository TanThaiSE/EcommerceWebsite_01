package com.nashtech.ecommerce_website.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="payment")
public class Payments {
	@Id
	private String id;
	@Column(name = "name")
	private String name;
	@OneToMany(mappedBy = "paymentsOrders")
	private Set<Orders> orders;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
