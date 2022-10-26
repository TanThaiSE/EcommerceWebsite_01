package com.nashtech.ecommerce_website.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="orders")
public class Orders {
	@Id
	@Column(name="id")
	private String id;
	
	@ManyToOne
	@JoinColumn(name="order_detail_id")
	private OrderDetail oDetail;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Products productOderDetail;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Accounts accountOrder;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OrderDetail getoDetail() {
		return oDetail;
	}

	public void setoDetail(OrderDetail oDetail) {
		this.oDetail = oDetail;
	}

	public Products getProductOderDetail() {
		return productOderDetail;
	}

	public void setProductOderDetail(Products productOderDetail) {
		this.productOderDetail = productOderDetail;
	}

	public Accounts getAccountOrder() {
		return accountOrder;
	}

	public void setAccountOrder(Accounts accountOrder) {
		this.accountOrder = accountOrder;
	}
	
	
}
