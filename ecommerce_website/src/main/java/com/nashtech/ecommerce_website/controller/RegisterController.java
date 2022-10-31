package com.nashtech.ecommerce_website.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nashtech.ecommerce_website.dto.request.RegisterRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.AccountsServiceImp;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/signup")
public class RegisterController {
	@Autowired
	AccountsServiceImp accountsServiceImp;
	
	@PostMapping("")
	public SuccessResponse signup(@Valid @RequestBody RegisterRequest registerRequest) {
		return accountsServiceImp.addToAccount(registerRequest);
	}
}
