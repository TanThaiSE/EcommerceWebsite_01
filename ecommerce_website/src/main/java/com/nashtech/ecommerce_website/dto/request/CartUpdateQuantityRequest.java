package com.nashtech.ecommerce_website.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CartUpdateQuantityRequest {
	private String id;
	private String productId;
	
	@Min(value = 1,message = "Quantity should be a positive number")
	private int quantity;
	private int price;
	@NotBlank(message = "accountId is required")
	private String accountId;
	private String sizeId;
	private String colorId;
	

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
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
	@Override
	public String toString() {
		return "CartsRequestDto [productId=" + productId + ", quantity=" + quantity + ", price=" + price
				+ ", accountId=" + accountId + ", sizeId=" + sizeId + ", colorId=" + colorId + "]";
	}
	
}
