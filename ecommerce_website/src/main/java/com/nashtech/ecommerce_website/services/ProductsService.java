package com.nashtech.ecommerce_website.services;
import com.nashtech.ecommerce_website.dto.response.ProductDetailResponseDto;
import com.nashtech.ecommerce_website.pojo.ListUpdateProductPojo;



public interface ProductsService {
	public ProductDetailResponseDto getDetailProduct(String productId);
	public ListUpdateProductPojo updateNumberBuyProduct(ListUpdateProductPojo productRequest);
}
