package com.nashtech.ecommerce_website.services;

import java.util.Map;

import com.nashtech.ecommerce_website.dto.request.NewAccountRequest;
import com.nashtech.ecommerce_website.dto.request.RegisterRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;

public interface AccountsService{
	public Map<String,Object> findByPhone(String phone);

	public SuccessResponse addToAccount(RegisterRequest registerRequest); 
	
	public SuccessResponse createNewAccount(NewAccountRequest newAccountRequest);
}
