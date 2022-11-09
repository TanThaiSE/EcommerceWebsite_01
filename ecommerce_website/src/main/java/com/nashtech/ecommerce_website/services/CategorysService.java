package com.nashtech.ecommerce_website.services;

import java.util.List;
import com.nashtech.ecommerce_website.dto.request.CategoryCreateRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Categorys;


public interface CategorysService  {
	public List<Categorys> getAllCategory();
	public SuccessResponse createNewCategory(CategoryCreateRequest categoryCreateRequest);
	public SuccessResponse updateCategory(CategoryCreateRequest categoryCreateRequest);
	public SuccessResponse getAllProductByCategory(String categoryId,int limit,int offset,String sortPrice);
	public SuccessResponse getAllCategories(int page,int offset);
	public SuccessResponse updateStatusCategory(String categoryId);
	public SuccessResponse deleteEmptyCategory(String categoryId);
}
