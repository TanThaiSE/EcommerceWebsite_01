package com.nashtech.ecommerce_website.pojo;

import java.util.Date;

public class OrderDetailPojo {
	private String id;
	private String productId;
	private int quantity;
	private int price;
	private String sizeId;
	private String colorId;
	private int totalPrice;


	public OrderDetailPojo(String id, String productId, int quantity, int price, String sizeId,
			String colorId,int totalPrice) {
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.sizeId = sizeId;
		this.colorId = colorId;
		this.totalPrice=totalPrice;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSizeId() {
		return sizeId;
	}
	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
}
