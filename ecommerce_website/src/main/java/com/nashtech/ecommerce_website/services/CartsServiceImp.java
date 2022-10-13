package com.nashtech.ecommerce_website.services;

import java.util.List;

import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.response.CartsResponse;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Carts;

public interface CartsServiceImp {
	public SuccessResponse addToCart(CartsRequestDto cartsRequestDto);
	
	public List<Carts> abc();
	
//	public void updateCart(Carts carts);
//	public void deleteCart(String id);
//	public void getCart(String accountId);
}
