package com.nashtech.ecommerce_website.dto.request;

import java.util.List;

public class CartsDeleteResponse {
	List<CartsRequestDto> prepareToDelete;

	public List<CartsRequestDto> getPrepareToDelete() {
		return prepareToDelete;
	}

	public void setPrepareToDelete(List<CartsRequestDto> prepareToDelete) {
		this.prepareToDelete = prepareToDelete;
	}

	public CartsDeleteResponse(List<CartsRequestDto> prepareToDelete,String k) {
		super();
		this.prepareToDelete = prepareToDelete;
	}
	
}
