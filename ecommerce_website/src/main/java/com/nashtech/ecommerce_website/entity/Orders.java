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
	
}
