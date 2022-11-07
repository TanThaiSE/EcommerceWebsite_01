package com.nashtech.ecommerce_website.services;

import com.nashtech.ecommerce_website.dto.request.ProductCreateRequest;
import com.nashtech.ecommerce_website.dto.request.ProductUpdateRequest;
import com.nashtech.ecommerce_website.dto.response.ProductDetailResponseDto;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.pojo.ListUpdateProductPojo;



public interface ProductsService {
	public ProductDetailResponseDto getDetailProduct(String productId);
	public ListUpdateProductPojo updateNumberBuyProduct(ListUpdateProductPojo listUpdateProductPojo);
	public SuccessResponse createNewProduct(ProductCreateRequest productCreateRequest);
	public SuccessResponse getAllProductCouldBeSearch(String searchKey,String sortPrice,int limit,int offset);
	public SuccessResponse updateStatusProduct(String productId);
	public SuccessResponse updateProduct(ProductUpdateRequest productUpdateRequest);
}
