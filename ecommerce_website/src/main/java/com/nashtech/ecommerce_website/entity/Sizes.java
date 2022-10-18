package com.nashtech.ecommerce_website.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="size")
public class Sizes {
	@Id
	private String id;
	@Column(name = "name")
	private String name;
	@OneToMany(mappedBy = "sizesCart")
	private Set<Carts> carts;
	@OneToMany(mappedBy = "sizesSizeProducts")
	private Set<SizeProducts> sizeProducts;
	
	@OneToMany(mappedBy = "sizeOrderDetails")
	private Set<OrderDetail> orderDetails ;
	
	
	
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
