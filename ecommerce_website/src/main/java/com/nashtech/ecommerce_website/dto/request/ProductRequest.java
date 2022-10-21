package com.nashtech.ecommerce_website.dto.request;

import java.util.Date;

public class ProductRequest {
	private String id;
	private String productId;
	private String detail;
	private String description;
	private int price;
	private Date createdDate;
	private Date updatedDate;
	private float rate; 
	private int numberBuy;
	
	
	public ProductRequest(String id, String productId, String detail, String description, int price, Date createdDate,
			Date updatedDate, float rate, int numberBuy) {
		this.id = id;
		this.productId = productId;
		this.detail = detail;
		this.description = description;
		this.price = price;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.rate = rate;
		this.numberBuy = numberBuy;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public int getNumberBuy() {
		return numberBuy;
	}
	public void setNumberBuy(int numberBuy) {
		this.numberBuy = numberBuy;
	}
	
}
