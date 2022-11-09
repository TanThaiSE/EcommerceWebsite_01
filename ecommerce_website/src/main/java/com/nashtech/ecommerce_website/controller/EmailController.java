package com.nashtech.ecommerce_website.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.EmailRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.EmailServiceImp;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
	@Autowired
	EmailServiceImp emailServiceImp;
	
	
	@PostMapping("")
	public SuccessResponse sendEmail(@Valid @RequestBody EmailRequest email) {
		return emailServiceImp.sendEmailToResetPassword(email);
	}
}
