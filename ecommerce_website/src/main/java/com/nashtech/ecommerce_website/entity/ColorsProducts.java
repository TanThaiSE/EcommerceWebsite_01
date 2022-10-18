package com.nashtech.ecommerce_website.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name="colorproduct")
public class ColorsProducts {
	
	@Id
	@Column(name="id")
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Products productsColorsProducts;
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Colors colorsColorsProducts;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Products getProductsColorsProducts() {
		return productsColorsProducts;
	}

	public void setProductsColorsProducts(Products productsColorsProducts) {
		this.productsColorsProducts = productsColorsProducts;
	}

	public Colors getColorsColorsProducts() {
		return colorsColorsProducts;
	}

	public void setColorsColorsProducts(Colors colorsColorsProducts) {
		this.colorsColorsProducts = colorsColorsProducts;
	}
	
	
	
//	@EmbeddedId
//	private ColorsProductsId id;
//	
//	@ManyToOne
//	@MapsId("product_id")
//	@JoinColumn(name = "product_id")
//	private Products productsColorsProducts;
//	
//	@ManyToOne
//	@MapsId("color_id")
//	@JoinColumn(name = "color_id")
//	private Colors colorsColorsProducts;
//
//	public ColorsProductsId getId() {
//		return id;
//	}
//
//	public void setId(ColorsProductsId id) {
//		this.id = id;
//	}
//
//	public Products getProductsColorsProducts() {
//		return productsColorsProducts;
//	}
//
//	public void setProductsColorsProducts(Products productsColorsProducts) {
//		this.productsColorsProducts = productsColorsProducts;
//	}
//
//	public Colors getColorsColorsProducts() {
//		return colorsColorsProducts;
//	}
//
//	public void setColorsColorsProducts(Colors colorsColorsProducts) {
//		this.colorsColorsProducts = colorsColorsProducts;
//	}



	
}
