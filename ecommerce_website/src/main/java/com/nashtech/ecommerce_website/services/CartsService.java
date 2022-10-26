package com.nashtech.ecommerce_website.services;

import java.util.List;

import com.nashtech.ecommerce_website.dto.request.CartUpdateQuantityRequest;
import com.nashtech.ecommerce_website.dto.request.CartsDeleteRequest;
import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.response.CartResponseDto;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;



public interface CartsService{
	public SuccessResponse addToCart(CartsRequestDto cartsRequestDto);
	public List<CartResponseDto> getAllProductInCartByAccountId();
	public CartResponseDto updateQuantityProductInCart(String id,CartUpdateQuantityRequest cartsRequestDto);
	public SuccessResponse deleteProductInCart(String id);
	public SuccessResponse deleteMutipleProductInCart(CartsDeleteRequest listProductCart);
}
