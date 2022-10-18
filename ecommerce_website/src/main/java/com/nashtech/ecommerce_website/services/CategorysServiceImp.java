package com.nashtech.ecommerce_website.services;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.response.ProductsInCategoryDto;
import com.nashtech.ecommerce_website.entity.Categorys;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.repository.CategorysRepository;

@Service
public class CategorysServiceImp implements CategorysService{
	private ModelMapper modelMapper=new ModelMapper();
	
	@Autowired
	CategorysRepository categorysRepository;

	
	@Override
	public List<Categorys> getAllCategory() {
		List<Categorys> category=categorysRepository.findAll();
		if(category==null||category.isEmpty()) {
			throw new NotFoundException("Cannot found category");
		}
		return category;
	}

	@Override
	public List<ProductsInCategoryDto> getAllProductByCategory(String categoryId) {
		List<ProductsInCategoryDto> products=categorysRepository.getAllProductByCategory(categoryId);
		if(products==null||products.isEmpty()) {
			throw new NotFoundException("Cannot found products in category");
		}
		return products;
	}
	
}
