package com.nashtech.ecommerce_website.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="historyorder")
public class HistoryOrders {
	@Id
	private String id;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders ordersHistoryOrders;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Orders getOrdersHistoryOrders() {
		return ordersHistoryOrders;
	}
	public void setOrdersHistoryOrders(Orders ordersHistoryOrders) {
		this.ordersHistoryOrders = ordersHistoryOrders;
	}
	
}
