package com.nashtech.ecommerce_website.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderDetailsId implements Serializable {
	@Column(name="order_id")
	private String orderId ;
	
	@Column(name="product_id")
	private String productId;

	public OrderDetailsId(String orderId, String productId) {
		this.orderId = orderId;
		this.productId = productId;
	}

	public OrderDetailsId() {

	}
}
