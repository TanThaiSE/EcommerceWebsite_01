package com.nashtech.ecommerce_website.pojo;

public class OrdersPojo {
	private String id;
	private String orderDetailId;
	private String productId;
	
	public OrdersPojo(String id, String orderDetailId, String productId) {
		this.id = id;
		this.orderDetailId = orderDetailId;
		this.productId = productId;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
