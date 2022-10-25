package com.nashtech.ecommerce_website.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.CartsDeleteRequest;

import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.request.LoginRequest;

import com.nashtech.ecommerce_website.dto.request.OrderDetailRequest;
import com.nashtech.ecommerce_website.dto.request.Test;
import com.nashtech.ecommerce_website.dto.response.CartResponseDto;


import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.pojo.OrderDetailPojo;

import com.nashtech.ecommerce_website.repository.CartsRepository;

import com.nashtech.ecommerce_website.services.CartsService;
import com.nashtech.ecommerce_website.services.CartsServiceImp;


@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = "*")
public class CartsController {
	@Autowired
	CartsServiceImp cartsServiceImp;
	
	@GetMapping("")
	public List<CartResponseDto> getAllProductInCart(){
		return  cartsServiceImp.getAllProductInCartByAccountId();
	}
	
	@PostMapping("")
	public SuccessResponse addToCart(@RequestBody CartsRequestDto cartsRequestDto ){
		return cartsServiceImp.addToCart(cartsRequestDto);
	}
	
	@PutMapping("/{id}/product")
	public CartResponseDto updateQuantityProductInCart(@PathVariable("id") String id ,@RequestBody CartsRequestDto cartsRequestDto ) {
		return cartsServiceImp.updateQuantityProductInCart(id,cartsRequestDto);
	}
		
	
	@DeleteMapping("/{idCart}")
	public SuccessResponse deleteProductInCart(@PathVariable("idCart")String id) {
		return cartsServiceImp.deleteProductInCart(id);
	}
	
	@DeleteMapping("")
	public SuccessResponse deleteMutipleProductInCart(@RequestBody CartsDeleteRequest c) {
		return cartsServiceImp.deleteMutipleProductInCart(c);
	}
}

/*viết unit test=>service, controller*/
