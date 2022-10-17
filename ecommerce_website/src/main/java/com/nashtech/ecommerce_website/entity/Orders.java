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

@Entity(name="orders")
public class Orders {
/* 
 * account, list order detail
 * 
 * */
	@Id
	private String id;

	
	@Column(name = "order_date")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date orderDate;
	
	@ManyToOne
	@JoinColumn(name = "payment_id")
	private Payments paymentsOrders;
	
	@ManyToOne
	@JoinColumn(name = "deliverystatus_id")
	private DeliveryStatus deliveryStatusOrders;
	
	@OneToMany(mappedBy = "ordersHistoryOrders")
	private Set<HistoryOrders> historyOrders;

	@OneToMany(mappedBy = "ordersOrderDetails")
	private Set<OrderDetails> orderDetails;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Payments getPaymentsOrders() {
		return paymentsOrders;
	}

	public void setPaymentsOrders(Payments paymentsOrders) {
		this.paymentsOrders = paymentsOrders;
	}

	public DeliveryStatus getDeliveryStatusOrders() {
		return deliveryStatusOrders;
	}

	public void setDeliveryStatusOrders(DeliveryStatus deliveryStatusOrders) {
		this.deliveryStatusOrders = deliveryStatusOrders;
	}
	
}
