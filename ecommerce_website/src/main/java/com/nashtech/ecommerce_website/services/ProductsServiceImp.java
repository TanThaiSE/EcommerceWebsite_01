package com.nashtech.ecommerce_website.services;

import java.util.List;
import java.util.Map;


public interface ProductsServiceImp {
	public List<Map<String,?>> getAllProductByCategory(String categoryId);
	
	public Map<String,Object> getDetailProduct(String productId);
	
}
