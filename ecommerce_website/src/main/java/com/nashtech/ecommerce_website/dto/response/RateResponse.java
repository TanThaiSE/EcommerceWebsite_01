package com.nashtech.ecommerce_website.dto.response;

import java.util.Date;

public class RateResponse {
	private String id;
	private int pointRate;
	private String comments;
	private Date ratingDate;
	private String name;
	
	public RateResponse() {

	}
	
	public RateResponse(String id, int pointRate, String comments, Date ratingDate, String name) {
		this.id = id;
		this.pointRate = pointRate;
		this.comments = comments;
		this.ratingDate = ratingDate;
		this.name = name;
	}
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getRatingDate() {
		return ratingDate;
	}
	public void setRatingDate(Date ratingDate) {
		this.ratingDate = ratingDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
