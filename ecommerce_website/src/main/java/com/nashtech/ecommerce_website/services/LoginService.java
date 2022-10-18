package com.nashtech.ecommerce_website.services;

import com.nashtech.ecommerce_website.dto.request.LoginRequest;
import com.nashtech.ecommerce_website.dto.response.LoginResponseDto;

public interface LoginService {
	public LoginResponseDto login(LoginRequest loginRequest);
}
