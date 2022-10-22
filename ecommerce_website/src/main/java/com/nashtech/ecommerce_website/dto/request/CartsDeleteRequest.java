package com.nashtech.ecommerce_website.dto.request;

import java.util.List;

public class CartsDeleteRequest {
	List<CartsRequestDto> prepareToDelete;

	public List<CartsRequestDto> getPrepareToDelete() {
		return prepareToDelete;
	}

	public void setPrepareToDelete(List<CartsRequestDto> prepareToDelete) {
		this.prepareToDelete = prepareToDelete;
	}

	public CartsDeleteRequest(List<CartsRequestDto> prepareToDelete,String k) {
		this.prepareToDelete = prepareToDelete;
	}
}
