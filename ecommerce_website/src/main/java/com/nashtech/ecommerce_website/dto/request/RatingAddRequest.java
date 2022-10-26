package com.nashtech.ecommerce_website.dto.request;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RatingAddRequest {
	private String id;
	
	@NotBlank(message = "orderDetailId is required")
	private String orderDetailId;
	@Min(value = 1,message = "pointRate must be at least 1")
	@Max(value=5,message = "pointRate must be maximum is 5")
	private int pointRate;
	
	@NotNull(message = "comment cannot null")
	private String comment;
	private Date ratingDate;
	public RatingAddRequest() {

	}
	

	public RatingAddRequest(String id, String orderDetailId, int pointRate, String comment, Date ratingDate) {
		this.id = id;
		this.orderDetailId = orderDetailId;
		this.pointRate = pointRate;
		this.comment = comment;
		this.ratingDate = ratingDate;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public int getPointRate() {
		return pointRate;
	}
	public void setPointRate(int pointRate) {
		this.pointRate = pointRate;
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
