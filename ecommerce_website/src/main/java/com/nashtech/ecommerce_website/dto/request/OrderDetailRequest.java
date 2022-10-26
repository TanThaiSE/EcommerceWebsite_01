package com.nashtech.ecommerce_website.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.nashtech.ecommerce_website.pojo.OrderDetailPojo;

public class OrderDetailRequest {
	private List<OrderDetailPojo> orderDetails;
	
	@NotBlank(message="address is required")
	private String address;
	@NotBlank(message="paymentId is required")
	private String paymentId;
	
	public OrderDetailRequest(List<OrderDetailPojo> orderDetails, String address, String paymentId) {
		this.orderDetails = orderDetails;
		this.address = address;
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
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	
}
 