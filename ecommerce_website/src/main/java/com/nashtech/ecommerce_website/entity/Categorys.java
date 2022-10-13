package com.nashtech.ecommerce_website.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "category")
public class Categorys {
	@Id
	private String id;
	@Column(name = "name")
	private String name;
	@Column(name="image")
	private String image;
	
	@OneToMany(mappedBy = "categorysProducts")
	private Set<Products> products;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
