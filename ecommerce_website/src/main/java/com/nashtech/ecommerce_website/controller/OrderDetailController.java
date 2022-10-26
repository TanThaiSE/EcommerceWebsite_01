package com.nashtech.ecommerce_website.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.request.OrderDetailRequest;
import com.nashtech.ecommerce_website.dto.response.OrderDetailResponse;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.OrderDetailServiceImp;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/orderdetail")
public class OrderDetailController {
	@Autowired
	OrderDetailServiceImp orderDetailServiceImp;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("")
	public SuccessResponse addToOrderDetail(@Valid @RequestBody OrderDetailRequest orderDetailRequest ){
		return orderDetailServiceImp.addToOrderDetail(orderDetailRequest);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("")
	public List<OrderDetailResponse> getAllProductInOrderDetail() {
		return orderDetailServiceImp.getAllProductInOrderDetail();
	}
	
}
