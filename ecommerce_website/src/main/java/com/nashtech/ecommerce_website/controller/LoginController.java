package com.nashtech.ecommerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nashtech.ecommerce_website.dto.request.LoginRequest;
import com.nashtech.ecommerce_website.dto.request.RegisterRequest;
import com.nashtech.ecommerce_website.dto.response.LoginResponseDto;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.LoginServiceImp;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class LoginController {
	@Autowired
	LoginServiceImp loginServiceImp;
	
	@PostMapping("/login")
	public LoginResponseDto login(@RequestBody LoginRequest loginRequest) {
		return loginServiceImp.login(loginRequest);
	}
}
