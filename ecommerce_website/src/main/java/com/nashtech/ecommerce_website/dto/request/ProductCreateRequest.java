package com.nashtech.ecommerce_website.dto.request;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;




public class ProductCreateRequest {
	private String id;
	@NotBlank(message = "name product is required")
	private String name;
	private String detail;
	private String description;
	@Min(value = 0, message = "price at least is 0")
	private int price;
	@NotBlank(message = "category id is required")
	private String categoryId;
	private Date createdDate;
	private Date updateDate;
	private float rate;
	private int numberBuy;

	public ProductCreateRequest(String id,String name, String detail, String description, int price, String categoryId,
			Date createdDate, Date updateDate, float rate, int numberBuy) {
		this.id=id;
		this.name = name;
		this.detail = detail;
		this.description = description;
		this.price = price;
		this.categoryId = categoryId;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.rate = rate;
		this.numberBuy = numberBuy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	
}
