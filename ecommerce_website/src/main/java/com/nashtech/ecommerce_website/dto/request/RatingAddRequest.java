package com.nashtech.ecommerce_website.dto.request;

public class RatingAddRequest {
	private String id;
	private String orderDetailId;
	private int pointRate;
	private String comment;
	
	public RatingAddRequest(String id, String orderDetailId, int pointRate, String comment) {
		this.id = id;
		this.orderDetailId = orderDetailId;
		this.pointRate = pointRate;
		this.comment = comment;
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
	
}
