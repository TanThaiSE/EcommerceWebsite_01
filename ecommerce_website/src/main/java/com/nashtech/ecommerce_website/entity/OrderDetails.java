package com.nashtech.ecommerce_website.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name="orderdetail")
public class OrderDetails {
	@EmbeddedId
	private OrderDetailsId id;
	
	@ManyToOne
	@MapsId("order_id")
	@JoinColumn(name="order_id")
	private Orders ordersOrderDetails;
	
	@ManyToOne
	@MapsId("product_id")
	@JoinColumn(name="product_id")
	private Products productOrderDetails;

	@ManyToOne
	@JoinColumn(name="account_id")
	private Accounts accountOrderDetails;
	
	public OrderDetailsId getId() {
		return id;
	}

	public void setId(OrderDetailsId id) {
		this.id = id;
	}

	public Orders getOrdersOrderDetails() {
		return ordersOrderDetails;
	}

	public void setOrdersOrderDetails(Orders ordersOrderDetails) {
		this.ordersOrderDetails = ordersOrderDetails;
	}

	public Products getProductOrderDetails() {
		return productOrderDetails;
	}

	public void setProductOrderDetails(Products productOrderDetails) {
		this.productOrderDetails = productOrderDetails;
	}

	public Accounts getAccountOrderDetails() {
		return accountOrderDetails;
	}

	public void setAccountOrderDetails(Accounts accountOrderDetails) {
		this.accountOrderDetails = accountOrderDetails;
	}
	
}
