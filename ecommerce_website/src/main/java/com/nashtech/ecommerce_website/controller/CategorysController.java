package com.nashtech.ecommerce_website.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nashtech.ecommerce_website.dto.response.ProductsInCategoryDto;
import com.nashtech.ecommerce_website.entity.Categorys;

import com.nashtech.ecommerce_website.services.CategorysService;
import com.nashtech.ecommerce_website.services.CategorysServiceImp;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/category")
public class CategorysController {
	@Autowired
	CategorysServiceImp categorysServiceImp;

	@GetMapping("")
	public List<Categorys> getAllCategory(){
		return categorysServiceImp.getAllCategory();
	}
	
	@GetMapping("/{idCategory}/product")
	public List<ProductsInCategoryDto> getProductsByCategoryId(@PathVariable("idCategory") String idCategory){
		return categorysServiceImp.getAllProductByCategory(idCategory);
	}

}
