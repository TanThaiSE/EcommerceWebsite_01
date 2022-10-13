package com.nashtech.ecommerce_website.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.repository.ProductsRepository;

@Service
public class ProductsService implements ProductsServiceImp {
	@Autowired
	ProductsRepository productsRepository;
	
	@Override
	public List<Map<String, ?>> getAllProductByCategory(String categoryId) {
		return productsRepository.getAllProductByCategory(categoryId);
	}

	@Override
	public Map<String,Object> getDetailProduct(String productId) {
		Map<String,Object> result=new HashMap<>();
		Map<String,Object> detailProduct=productsRepository.getDetailProduct(productId);
	
		List<Map<String,Object>> getColorProduct=productsRepository.getColorProduct(productId);
		List<Map<String,Object>> getSizeProduct=productsRepository.getSizeProduct(productId);
		List<Map<String,Object>> getImageProduct=productsRepository.getImageProduct(productId);
		
		result.putAll(detailProduct);
		result.put("colorProduct", getColorProduct);
		result.put("sizeProduct", getSizeProduct);
		result.put("imageProduct", getImageProduct);
		return result;
	}


}
