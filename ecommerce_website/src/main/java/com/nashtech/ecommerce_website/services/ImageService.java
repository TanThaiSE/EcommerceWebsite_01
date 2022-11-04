package com.nashtech.ecommerce_website.services;


import com.nashtech.ecommerce_website.dto.request.ImageAddRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;

public interface ImageService {
	public SuccessResponse createImageProduct(ImageAddRequest imageAddRequest);
}
