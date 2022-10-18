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

public interface CategorysService  {
	public List<Categorys> getAllCategory();
	public List<ProductsInCategoryDto> getAllProductByCategory(String categoryId);
}
