package com.nashtech.ecommerce_website.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.response.CartResponseDto;


import com.nashtech.ecommerce_website.dto.response.SuccessResponse;

import com.nashtech.ecommerce_website.repository.CartsRepository;

import com.nashtech.ecommerce_website.services.CartsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/cart")
public class CartsController {
	@Autowired
	CartsService cartsService;
	
	@GetMapping("")
	public List<CartResponseDto> getAllProductInCart(){
		return  cartsService.getAllProductInCartByAccountId();
	}
	
	@PostMapping("")
	public CartResponseDto addToCart(@RequestBody CartsRequestDto cartsRequestDto ){
		return cartsService.addToCart(cartsRequestDto);
	}
	
	@PutMapping("/{id}/product")
	public CartResponseDto updateQuantityProductInCart(@PathVariable("id") String id ,@RequestBody CartsRequestDto cartsRequestDto ) {
		return cartsService.updateQuantityProductInCart(id,cartsRequestDto);
	}
	
	@DeleteMapping("/{idProduct}")
	public SuccessResponse deleteProductInCart(@PathVariable("idProduct")String idProduct) {
		return cartsService.deleteProductInCart(idProduct);
	}
}
