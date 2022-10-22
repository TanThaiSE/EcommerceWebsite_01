package com.nashtech.ecommerce_website.dto.response;

public class OrderDetailResponse {
	private String id;
	private int quantity;
	private int price;
	private int totalPrice;
	private String productId;
	private String nameProduct;
	private String ratingId;
	
	public OrderDetailResponse(String id, int quantity, int price, int totalPrice, String productId, String nameProduct,
			String ratingId) {
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
		this.productId = productId;
		this.nameProduct = nameProduct;
		this.ratingId = ratingId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public String getRatingId() {
		return ratingId;
	}
	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}
	
}
