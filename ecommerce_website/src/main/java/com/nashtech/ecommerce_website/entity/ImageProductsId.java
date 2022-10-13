package com.nashtech.ecommerce_website.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ImageProductsId implements Serializable {
	@Column(name="product_id")
	private String productId ;
	
	@Column(name="image_id")
	private String imageId;

	public ImageProductsId(String productId, String imageId) {
		this.productId = productId;
		this.imageId = imageId;
	}

	public ImageProductsId() {
		
	}
}
