package com.nashtech.ecommerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.CategoryCreateRequest;
import com.nashtech.ecommerce_website.dto.request.NewAccountRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.AccountsServiceImp;
import com.nashtech.ecommerce_website.services.CategorysServiceImp;
import com.nashtech.ecommerce_website.services.ProfileServiceImp;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	@Autowired
	AccountsServiceImp accountsServiceImp;
	@Autowired
	ProfileServiceImp profileServiceImp;
	@Autowired
	CategorysServiceImp categorysServiceImp;
	
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
	
	@PostMapping("/category")
	public SuccessResponse createNewCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
		return categorysServiceImp.createNewCategory(categoryCreateRequest);
	}
	
	@PutMapping("/category")
	public SuccessResponse updateCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
		return categorysServiceImp.updateCategory(categoryCreateRequest);
	}
	
}
