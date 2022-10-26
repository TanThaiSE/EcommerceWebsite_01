package com.nashtech.ecommerce_website.dto.request;

import java.util.List;

import com.nashtech.ecommerce_website.pojo.CartDeletePojo;

public class CartsDeleteRequest {
	List<CartDeletePojo> prepareToDelete;

	public List<CartDeletePojo> getPrepareToDelete() {
		return prepareToDelete;
	}

	public void setPrepareToDelete(List<CartDeletePojo> prepareToDelete) {
		this.prepareToDelete = prepareToDelete;
	}

	public CartsDeleteRequest(List<CartDeletePojo> prepareToDelete,String k) {
		this.prepareToDelete = prepareToDelete;
	}
}
