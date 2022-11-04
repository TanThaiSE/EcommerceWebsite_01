package com.nashtech.ecommerce_website.services;

import com.nashtech.ecommerce_website.dto.request.AttributeAddRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;

public interface SizeProductService {
	public SuccessResponse createSizeProduct(AttributeAddRequest addRequest);
}
