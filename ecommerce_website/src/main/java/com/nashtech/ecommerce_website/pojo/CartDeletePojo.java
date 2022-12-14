package com.nashtech.ecommerce_website.pojo;


import javax.validation.constraints.NotBlank;

public class CartDeletePojo {
	@NotBlank(message = "idCart is required")
	private String id;
	private String productId;
	private int quantity;
	private int price;
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
