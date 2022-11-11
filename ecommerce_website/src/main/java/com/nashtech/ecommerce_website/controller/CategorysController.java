package com.nashtech.ecommerce_website.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.CategoryCreateRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Categorys;
import com.nashtech.ecommerce_website.services.CategorysServiceImp;


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
	public SuccessResponse getProductsByCategoryId(@RequestParam(name = "sortPrice",defaultValue = "desc") String sortPrice,@PathVariable("idCategory") String idCategory,@RequestParam(name = "page",defaultValue = "0") int page,@RequestParam(name = "offset",defaultValue = "1") int offset){
		return categorysServiceImp.getAllProductByCategory(idCategory,page,offset,sortPrice);
	}
	
	@PostMapping("")
	public SuccessResponse createNewCategory(@Valid @RequestBody CategoryCreateRequest categoryCreateRequest) {
		return categorysServiceImp.createNewCategory(categoryCreateRequest);
	}
	
	@PutMapping("")
	public SuccessResponse updateCategory(@Valid @RequestBody CategoryCreateRequest categoryCreateRequest) {
		return categorysServiceImp.updateCategory(categoryCreateRequest);
	}
	
	@GetMapping("/manager")
	public SuccessResponse getAllCategoriesManager(@RequestParam(name = "page",defaultValue = "0") int page,@RequestParam(name = "offset",defaultValue = "1") int offset) {
		return categorysServiceImp.getAllCategories(page, offset);
	}
	@DeleteMapping("/{idCategory}")
	public SuccessResponse deleteEmptyCategory(@PathVariable("idCategory") String idCategory) {
		return categorysServiceImp.deleteEmptyCategory(idCategory);
	}
}
