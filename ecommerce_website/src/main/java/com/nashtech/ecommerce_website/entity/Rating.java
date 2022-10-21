package com.nashtech.ecommerce_website.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name="rating")
public class Rating {
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="point_rate")
	private int pointRate;
	
	@Column(name="comment")
	private String comment;
	
	@OneToOne
	@JoinColumn(name="order_detail_id")
	private OrderDetail orderDetailRating;

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


	public OrderDetail getOrderDetailRating() {
		return orderDetailRating;
	}

	public void setOrderDetailRating(OrderDetail orderDetailRating) {
		this.orderDetailRating = orderDetailRating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
