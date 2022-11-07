package com.nashtech.ecommerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.PaymentServiceImp;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
	@Autowired
	PaymentServiceImp paymentServiceImp;
	@GetMapping("")
	public SuccessResponse getAllPayment() {
		return paymentServiceImp.getAllPayment();
	}
}
