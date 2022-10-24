package com.nashtech.ecommerce_website.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@Column(name = "rating_date")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date ratingDate;
	
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

	public Date getRatingDate() {
		return ratingDate;
	}

	public void setRatingDate(Date ratingDate) {
		this.ratingDate = ratingDate;
	}
	
	
}
