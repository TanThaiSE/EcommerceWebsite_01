package com.nashtech.ecommerce_website.services;

import java.util.List;
import java.util.Map;

import com.nashtech.ecommerce_website.dto.response.ProductDetailResponseDto;


public interface ProductsServiceImp {
	public ProductDetailResponseDto getDetailProduct(String productId);
	
}
