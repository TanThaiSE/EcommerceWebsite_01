package com.nashtech.ecommerce_website.pojo;

import java.util.List;

import com.nashtech.ecommerce_website.dto.response.ProductUpdateResponse;

public class ListUpdateProductPojo {
	private List<ProductUpdateResponse> prepareToUpdate;

	public ListUpdateProductPojo(List<ProductUpdateResponse> prepareToUpdate,String k) {
		this.prepareToUpdate = prepareToUpdate;
	}

	public List<ProductUpdateResponse> getPrepareToUpdate() {
		return prepareToUpdate;
	}

	public void setPrepareToUpdate(List<ProductUpdateResponse> prepareToUpdate) {
		this.prepareToUpdate = prepareToUpdate;
	}
	
}
