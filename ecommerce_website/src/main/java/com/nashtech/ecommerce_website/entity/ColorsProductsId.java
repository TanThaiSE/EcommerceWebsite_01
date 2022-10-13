package com.nashtech.ecommerce_website.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ColorsProductsId implements Serializable {
	@Column(name="product_id")
	private String productId;
	
	@Column(name="color_id")
	private String colorId;

	public ColorsProductsId(String productId, String colorId) {
		this.productId = productId;
		this.colorId = colorId;
	}
	
	public ColorsProductsId() {

	}
	
}
