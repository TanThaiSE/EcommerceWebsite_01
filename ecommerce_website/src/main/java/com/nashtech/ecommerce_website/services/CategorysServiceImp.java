package com.nashtech.ecommerce_website.services;

import java.util.List;
import java.util.Map;

import com.nashtech.ecommerce_website.dto.response.ProductsInCategoryDto;
import com.nashtech.ecommerce_website.entity.Categorys;

public interface CategorysServiceImp {
	public List<Categorys> getAllCategory();
	public List<ProductsInCategoryDto> getAllProductByCategory(String categoryId);
}
