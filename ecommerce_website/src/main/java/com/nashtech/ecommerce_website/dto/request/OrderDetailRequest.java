package com.nashtech.ecommerce_website.dto.request;

import java.util.List;

import com.nashtech.ecommerce_website.pojo.OrderDetailPojo;

public class OrderDetailRequest {
	private List<OrderDetailPojo> orderDetails;
	private String address;
	private String deliveryId;
	private String paymentId;
	
	public OrderDetailRequest(List<OrderDetailPojo> orderDetails, String address, String deliveryId, String paymentId) {
		this.orderDetails = orderDetails;
		this.address = address;
		this.deliveryId = deliveryId;
		this.paymentId = paymentId;
	}
	public List<OrderDetailPojo> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetailPojo> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	
}
 