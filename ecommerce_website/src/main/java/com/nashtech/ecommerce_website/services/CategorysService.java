package com.nashtech.ecommerce_website.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.repository.CategorysRepository;

@Service
public class CategorysService implements CategorysServiceImp {
	@Autowired
	CategorysRepository categorysRepository;
	
	@Override
	public List<Map<String, ?>> getAllCategory() {
		// TODO Auto-generated method stub
		return categorysRepository.getAllCategories();
	}

}
