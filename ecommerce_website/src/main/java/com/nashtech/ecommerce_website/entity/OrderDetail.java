package com.nashtech.ecommerce_website.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="orderdetail")
public class OrderDetail {

	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="delivery_id")
	private DeliveryStatus deliveryStatusOrdersDetails;
	
	@ManyToOne
	@JoinColumn(name="payment_id")
	private Payments paymentsOrdersDetails;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Accounts accountOrderDetails;
	
	@ManyToOne
	@JoinColumn(name="color_id")
	private Colors colorOrderDetails;
	
	@ManyToOne
	@JoinColumn(name="size_id")
	private Sizes sizeOrderDetails;
	
	@Column(name="price")
	private String price;
	
	@Column(name = "order_date")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date orderDate;
	
	@OneToMany(mappedBy = "oDetail")
	private Set<Orders> orders;
	
	@OneToMany(mappedBy = "orderDetail")
	private Set<Rating> ratings;
	
	@Column(name = "address")
	private String address;
	
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

	public DeliveryStatus getDeliveryStatusOrdersDetails() {
		return deliveryStatusOrdersDetails;
	}

	public void setDeliveryStatusOrdersDetails(DeliveryStatus deliveryStatusOrdersDetails) {
		this.deliveryStatusOrdersDetails = deliveryStatusOrdersDetails;
	}

	public Payments getPaymentsOrdersDetails() {
		return paymentsOrdersDetails;
	}

	public void setPaymentsOrdersDetails(Payments paymentsOrdersDetails) {
		this.paymentsOrdersDetails = paymentsOrdersDetails;
	}

	public Accounts getAccountOrderDetails() {
		return accountOrderDetails;
	}

	public void setAccountOrderDetails(Accounts accountOrderDetails) {
		this.accountOrderDetails = accountOrderDetails;
	}

	public Colors getColorOrderDetails() {
		return colorOrderDetails;
	}

	public void setColorOrderDetails(Colors colorOrderDetails) {
		this.colorOrderDetails = colorOrderDetails;
	}

	public Sizes getSizeOrderDetails() {
		return sizeOrderDetails;
	}

	public void setSizeOrderDetails(Sizes sizeOrderDetails) {
		this.sizeOrderDetails = sizeOrderDetails;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	
}
