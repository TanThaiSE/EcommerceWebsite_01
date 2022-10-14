package com.nashtech.ecommerce_website.services;

import java.util.List;

import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.response.CartResponseDto;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;


public interface CartsServiceImp {
	public CartResponseDto addToCart(CartsRequestDto cartsRequestDto);
	public List<CartResponseDto> getAllProductInCartByAccountId();
	public CartResponseDto updateQuantityProductInCart(String id,CartsRequestDto cartsRequestDto);
	public SuccessResponse deleteProductInCart(String id);
}
