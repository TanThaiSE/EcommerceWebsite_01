package com.nashtech.ecommerce_website.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name="sizeproduct")
public class SizeProducts {
	
	@Id
	@Column(name="id")
	private String id;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Products productsSizeProducts;
	
	@ManyToOne
	@JoinColumn(name="size_id")
	private Sizes sizesSizeProducts;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Products getProductsSizeProducts() {
		return productsSizeProducts;
	}

	public void setProductsSizeProducts(Products productsSizeProducts) {
		this.productsSizeProducts = productsSizeProducts;
	}

	public Sizes getSizesSizeProducts() {
		return sizesSizeProducts;
	}

	public void setSizesSizeProducts(Sizes sizesSizeProducts) {
		this.sizesSizeProducts = sizesSizeProducts;
	}
	
	
	
//	@EmbeddedId
//	private SizeProductsId id;
	
//	@ManyToOne
//	@MapsId("product_id")
//	@JoinColumn(name="product_id")
//	private Products productsSizeProducts;
//	
//	@ManyToOne
//	@MapsId("size_id")
//	@JoinColumn(name="size_id")
//	private Sizes sizesSizeProducts;

//	public SizeProductsId getId() {
//		return id;
//	}
//
//	public void setId(SizeProductsId id) {
//		this.id = id;
//	}

//	public Products getProductsSizeProducts() {
//		return productsSizeProducts;
//	}
//
//	public void setProductsSizeProducts(Products productsSizeProducts) {
//		this.productsSizeProducts = productsSizeProducts;
//	}
//
//	public Sizes getSizesSizeProducts() {
//		return sizesSizeProducts;
//	}
//
//	public void setSizesSizeProducts(Sizes sizesSizeProducts) {
//		this.sizesSizeProducts = sizesSizeProducts;
//	}


	
}
