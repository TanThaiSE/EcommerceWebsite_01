package com.nashtech.ecommerce_website.dto.response;

import java.util.Date;

public class ProductUpdateResponse {
	private String productId;
	private String nameProduct;
	private String detail;
	private String description;
	private int price;
	private Date createdDate;
	private Date updatedDate;
	private float rate;
	private int quantity;
	
	public ProductUpdateResponse(String productId, String nameProduct, String detail, String description, int price,
			Date createdDate, Date updatedDate, float rate, int quantity) {
		this.productId = productId;
		this.nameProduct = nameProduct;
		this.detail = detail;
		this.description = description;
		this.price = price;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.rate = rate;
		this.quantity = quantity;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
