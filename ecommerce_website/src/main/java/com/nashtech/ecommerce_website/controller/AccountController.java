package com.nashtech.ecommerce_website.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.AccountCreateRequest;
import com.nashtech.ecommerce_website.dto.request.AccountUpdatePasswordRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.AccountsServiceImp;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
	@Autowired
	AccountsServiceImp accountsServiceImp;
	
	@PutMapping("")
	public SuccessResponse updatePassword(@Valid @RequestBody AccountUpdatePasswordRequest accountUpdatePasswordRequest) {
		return accountsServiceImp.updatePassword(accountUpdatePasswordRequest);
	}
	
	@PostMapping("")
	public SuccessResponse createAccount(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
		return accountsServiceImp.createNewAccount(accountCreateRequest);
	}
}
