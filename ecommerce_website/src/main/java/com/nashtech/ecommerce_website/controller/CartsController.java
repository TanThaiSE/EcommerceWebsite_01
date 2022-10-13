package com.nashtech.ecommerce_website.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.response.CartsResponse;
import com.nashtech.ecommerce_website.dto.response.FindProductInCartResponseDto;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Carts;
import com.nashtech.ecommerce_website.repository.CartsRepository;
import com.nashtech.ecommerce_website.services.CartsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/cart")
public class CartsController {
	@Autowired
	CartsService cartsService;
	
//	//test
	@Autowired
	CartsRepository cartsRepository;
	
//	@PostMapping("")
//	public SuccessResponse addToCart(@RequestBody CartsRequestDto cartsRequestDto ){
//		return cartsService.addToCart(cartsRequestDto);
//	}
	
//	@GetMapping("")
	
	
//	@GetMapping("/{idAccount}")
//	public List<Carts> findProductInCart(@PathVariable("idAccount") String idAccount ){
//		return cartsRepository.findByAccountCart_Id(idAccount);
//		
////		return cartsRepository.findAll();
//	}
	
	@GetMapping("/abcd")
	public List<Carts> findALL(){
		return cartsService.abc();
//		return cartsRepository.findAll();
	}
	
}
