package com.nashtech.ecommerce_website.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SizeProductsId implements Serializable{
	@Column(name="product_id")
	private String productId;
	
	@Column(name="size_id")
	private String sizeId;

	public SizeProductsId(String productId, String sizeId) {
		this.productId = productId;
		this.sizeId = sizeId;
	}
	
	public SizeProductsId() {

	}
}
