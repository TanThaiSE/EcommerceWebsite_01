package com.nashtech.ecommerce_website.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="rating")
public class Rating {
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="point_rate")
	private int pointRate;
	
	@ManyToOne
	@JoinColumn(name="order_detail_id")
	private OrderDetail orderDetail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPointRate() {
		return pointRate;
	}

	public void setPointRate(int pointRate) {
		this.pointRate = pointRate;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	
	
}
