package com.nashtech.ecommerce_website.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="color")
public class Colors {
	@Id
	private String id;
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "colorsCart")
	private Set<Carts> carts;
	
	@OneToMany(mappedBy = "colorsColorsProducts")
	private Set<ColorsProducts> colorsProducts;

	@OneToMany(mappedBy = "colorOrderDetails")
	private Set<OrderDetail> orderDetails;
	
	
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
