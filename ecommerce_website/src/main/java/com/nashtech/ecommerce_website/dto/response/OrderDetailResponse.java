package com.nashtech.ecommerce_website.dto.response;

public class OrderDetailResponse {
	private String id;
	private int quantity;
	private int price;
	private int totalPrice;
	private String productId;
	private String productName;
	private String ratingId;
	private String imgName;
	private String sizeName;
	private String colorName;
	
	
	public OrderDetailResponse() {

	}
	public OrderDetailResponse(String id, int quantity, int price, int totalPrice, String productId, String productName,
			String ratingId, String imgName, String sizeName, String colorName) {
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
		this.productId = productId;
		this.productName = productName;
		this.ratingId = ratingId;
		this.imgName = imgName;
		this.sizeName = sizeName;
		this.colorName = colorName;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getRatingId() {
		return ratingId;
	}
	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	

	
}
