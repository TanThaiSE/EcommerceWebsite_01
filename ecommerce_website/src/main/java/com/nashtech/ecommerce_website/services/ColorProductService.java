package com.nashtech.ecommerce_website.services;

import com.nashtech.ecommerce_website.dto.request.AttributeAddRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;


public interface ColorProductService {
	public SuccessResponse createColorProduct(AttributeAddRequest addRequest);
}
