package com.nashtech.ecommerce_website.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="cart")
public class Carts implements Serializable{
	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Products productsCart;

	@Column(name="quantity")
	private int quantity;
	
	@Column(name="price")
	private int price;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Accounts accountCart;

	@ManyToOne
	@JoinColumn(name="size_id")
	private Sizes sizesCart;
	
	@ManyToOne
	@JoinColumn(name="color_id")
	private Colors colorsCart;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Accounts getAccountCart() {
		return accountCart;
	}

	public void setAccountCart(Accounts accountCart) {
		this.accountCart = accountCart;
	}

	public Sizes getSizesCart() {
		return sizesCart;
	}

	public void setSizesCart(Sizes sizesCart) {
		this.sizesCart = sizesCart;
	}

	public Colors getColorsCart() {
		return colorsCart;
	}

	public void setColorsCart(Colors colorsCart) {
		this.colorsCart = colorsCart;
	}


	public Products getProductsCart() {
		return productsCart;
	}

	public void setProductsCart(Products productsCart) {
		this.productsCart = productsCart;
	}




	
}
