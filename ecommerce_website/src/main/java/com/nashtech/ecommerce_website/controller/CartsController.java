package com.nashtech.ecommerce_website.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.CartUpdateQuantityRequest;
import com.nashtech.ecommerce_website.dto.request.CartsDeleteRequest;
import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.response.CartResponseDto;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.CartsServiceImp;


@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = "*")
public class CartsController {
	@Autowired
	CartsServiceImp cartsServiceImp;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("")
	public List<CartResponseDto> getAllProductInCart(){
		return  cartsServiceImp.getAllProductInCartByAccountId();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("")
	public SuccessResponse addToCart(@Valid @RequestBody CartsRequestDto cartsRequestDto ){
		return cartsServiceImp.addToCart(cartsRequestDto);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PutMapping("/{id}/product")
	public CartResponseDto updateQuantityProductInCart(@PathVariable("id") String id ,@Valid @RequestBody CartUpdateQuantityRequest cartUpdateQuantityRequest ) {
		return cartsServiceImp.updateQuantityProductInCart(id,cartUpdateQuantityRequest);
	}		
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@DeleteMapping("/{idCart}")
	public SuccessResponse deleteProductInCart(@PathVariable("idCart")String id) {
		return cartsServiceImp.deleteProductInCart(id);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@DeleteMapping("")
	public SuccessResponse deleteMutipleProductInCart(@Valid @RequestBody CartsDeleteRequest cartsDeleteRequest) {
		return cartsServiceImp.deleteMutipleProductInCart(cartsDeleteRequest);
	}
}

/*viết unit test=>service, controller*/
