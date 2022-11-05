package com.nashtech.ecommerce_website.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.AttributeAddRequest;
import com.nashtech.ecommerce_website.dto.request.CategoryCreateRequest;
import com.nashtech.ecommerce_website.dto.request.ImageAddRequest;
import com.nashtech.ecommerce_website.dto.request.NewAccountRequest;
import com.nashtech.ecommerce_website.dto.request.ProductCreateRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.AccountsServiceImp;
import com.nashtech.ecommerce_website.services.CategorysServiceImp;

import com.nashtech.ecommerce_website.services.ColorProductServiceImp;
import com.nashtech.ecommerce_website.services.ColorServiceImp;
import com.nashtech.ecommerce_website.services.ImageServiceImp;
import com.nashtech.ecommerce_website.services.ProductsServiceImp;
import com.nashtech.ecommerce_website.services.ProfileServiceImp;
import com.nashtech.ecommerce_website.services.SizeProductServiceImp;
import com.nashtech.ecommerce_website.services.SizeServiceImp;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	@Autowired
	AccountsServiceImp accountsServiceImp;
	@Autowired
	ProfileServiceImp profileServiceImp;
	@Autowired
	CategorysServiceImp categorysServiceImp;
	@Autowired
	ProductsServiceImp productsServiceImp;
	@Autowired
	SizeServiceImp sizeServiceImp;
	@Autowired
	ColorServiceImp colorServiceImp;
	@Autowired
	ColorProductServiceImp colorProductServiceImp;
	@Autowired
	SizeProductServiceImp sizeProductServiceImp;
	@Autowired
	ImageServiceImp imageServiceImp;
	
	@PostMapping("/new-account")
	public SuccessResponse createNewAccount(@RequestBody NewAccountRequest newAccountRequest) {
		return accountsServiceImp.createNewAccount(newAccountRequest);
	}
	
	@GetMapping("/users")
	public SuccessResponse getAllUsers(@RequestParam(name = "page",defaultValue = "0") int page,@RequestParam(name = "offset",defaultValue = "1") int offset){
		return profileServiceImp.getAllUsers(page,offset);
	}
	
	@PutMapping("/{idAccount}/access-rights")
	public SuccessResponse updateBlockedUsers(@PathVariable("idAccount")String idAccount) {
		return profileServiceImp.updateBlockedUsers(idAccount);
	}
	
	@GetMapping("/category")
	public SuccessResponse getAllCategories(@RequestParam(name = "page",defaultValue = "0") int page,@RequestParam(name = "offset",defaultValue = "1") int offset) {
		return categorysServiceImp.getAllCategories(page, offset);
	}
	
	@PostMapping("/category")
	public SuccessResponse createNewCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
		return categorysServiceImp.createNewCategory(categoryCreateRequest);
	}
	
	@PutMapping("/category")
	public SuccessResponse updateCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
		return categorysServiceImp.updateCategory(categoryCreateRequest);
	}
	
//	@DeleteMapping("/category")
//	public SuccessResponse deleteCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
//		return null;
//	}
	
	@PostMapping("/product")
	public SuccessResponse createNewProduct(@Valid @RequestBody ProductCreateRequest productCreate) {
		return productsServiceImp.createNewProduct(productCreate);
	}
	
//	@PutMapping("/product")
//	public SuccessResponse updateProduct(@RequestBody) {
//		return null;
//	}
	
	@PutMapping("/product/{idProduct}/status-product")
	public SuccessResponse updateStatusProduct(@PathVariable("idProduct")String idProduct) {
		return productsServiceImp.updateStatusProduct(idProduct);
	}
	
	@GetMapping("/size")
	public SuccessResponse getAllSize() {
		return sizeServiceImp.getAllSize();
	}
	@PostMapping("/size")
	public SuccessResponse createSizeForProduct(@Valid @RequestBody AttributeAddRequest addRequest) {
		return sizeProductServiceImp.createSizeProduct(addRequest);
	}
	@GetMapping("/color")
	public SuccessResponse getAllColor() {
		return colorServiceImp.getAllColor();
	}
	@PostMapping("/color")
	public SuccessResponse createColorForProduct(@Valid @RequestBody AttributeAddRequest addRequest) {
		return colorProductServiceImp.createColorProduct(addRequest);
	}
	@PostMapping("/image")
	public SuccessResponse createImageForProduct(@Valid @RequestBody ImageAddRequest imageAddRequest) {
		return imageServiceImp.createImageProduct(imageAddRequest);
	}
}
