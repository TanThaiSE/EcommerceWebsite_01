package com.nashtech.ecommerce_website.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping("")
	public SuccessResponse addToOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest ){
		return orderDetailServiceImp.addToOrderDetail(orderDetailRequest);
	}
	
	@GetMapping("")
	public List<OrderDetailResponse> getAllProductInOrderDetail() {
		return orderDetailServiceImp.getAllProductInOrderDetail();
	}
	
}
