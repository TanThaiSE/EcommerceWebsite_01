package com.nashtech.ecommerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.ProfileRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.ProfileServiceImp;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
	@Autowired
	ProfileServiceImp profileServiceImp;
	
	@PostMapping("")
	public SuccessResponse addToProfile(@RequestBody ProfileRequest profileRequest) {
		return profileServiceImp.addToProfile(profileRequest);
	}
}
