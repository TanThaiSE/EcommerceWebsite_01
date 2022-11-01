package com.nashtech.ecommerce_website.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.CategoryCreateRequest;
import com.nashtech.ecommerce_website.dto.response.ProductsInCategoryDto;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
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
	public List<ProductsInCategoryDto> getAllProductByCategory(String categoryId,int page,int pageSize) {
		Pageable pageable=PageRequest.of(page,pageSize);
		Page<ProductsInCategoryDto> products=categorysRepository.getAllProductByCategory(categoryId,pageable);
		if(products==null||products.isEmpty()) {
			throw new NotFoundException("Cannot found products in category");
		}
		return products.getContent();
	}

	@Override
	public SuccessResponse createNewCategory(CategoryCreateRequest categoryCreateRequest) {
		String idCategory = UUID.randomUUID().toString();
		categoryCreateRequest.setId(idCategory);
		categorysRepository.createNewCategory(categoryCreateRequest);
		SuccessResponse successResponse=new SuccessResponse("200","create new category success",categoryCreateRequest);
		return successResponse;
	}

	@Override
	public SuccessResponse updateCategory(CategoryCreateRequest categoryCreateRequest) {
		Optional<Categorys> categorys=categorysRepository.findById(categoryCreateRequest.getId());
		if(categorys.isEmpty()) {
			throw new NotFoundException("Not found category");
		}
		categorysRepository.updateCategory(categoryCreateRequest);
		SuccessResponse successResponse=new SuccessResponse("201","Update category success",categoryCreateRequest);
		return successResponse;
	}
	
}
