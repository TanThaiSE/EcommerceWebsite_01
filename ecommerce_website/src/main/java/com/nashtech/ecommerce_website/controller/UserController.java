package com.nashtech.ecommerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.ProfileServiceImp;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	@Autowired
	ProfileServiceImp profileServiceImp;
	@GetMapping("")
	public SuccessResponse getAllUsers(@RequestParam(name = "page",defaultValue = "0") int page,@RequestParam(name = "offset",defaultValue = "1") int offset){
		return profileServiceImp.getAllUsers(page,offset);
	}
	@PutMapping("/{idAccount}/access-rights")
	public SuccessResponse updateBlockedUsers(@PathVariable("idAccount")String idAccount) {
		return profileServiceImp.updateBlockedUsers(idAccount);
	}
}
