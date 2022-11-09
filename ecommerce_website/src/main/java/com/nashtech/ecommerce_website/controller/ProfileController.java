package com.nashtech.ecommerce_website.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nashtech.ecommerce_website.dto.request.ProfileRequest;
import com.nashtech.ecommerce_website.dto.request.ProfileUpdateInfoRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.ProfileServiceImp;


@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
	@Autowired
	ProfileServiceImp profileServiceImp;
	
	@PostMapping("")
	public SuccessResponse addToProfile(@RequestBody ProfileRequest profileRequest) {
		return profileServiceImp.addToProfile(profileRequest);
	}
	
	@GetMapping("/{idAccount}/detail-infor")
	public SuccessResponse showDetailProfile(@PathVariable("idAccount")String idAccount) {
		return profileServiceImp.showDetailProfile(idAccount);
	}
	
	@PutMapping("")
	public SuccessResponse updateInforProfile(@Valid @RequestBody ProfileUpdateInfoRequest profileUpdateInfoRequest) {
		return profileServiceImp.updateInfoUser(profileUpdateInfoRequest);
	}
	

}
