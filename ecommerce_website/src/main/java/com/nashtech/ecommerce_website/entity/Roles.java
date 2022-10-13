package com.nashtech.ecommerce_website.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "role")
public class Roles {
	@Id
	private String id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy = "roles")
	private Set<Accounts> accounts;

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
