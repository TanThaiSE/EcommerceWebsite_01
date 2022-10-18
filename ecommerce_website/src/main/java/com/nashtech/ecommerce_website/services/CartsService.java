package com.nashtech.ecommerce_website.services;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.response.CartResponseDto;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Carts;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.repository.CartsRepository;


public interface CartsService{
	public SuccessResponse addToCart(CartsRequestDto cartsRequestDto);
	public List<CartResponseDto> getAllProductInCartByAccountId();
	public CartResponseDto updateQuantityProductInCart(String id,CartsRequestDto cartsRequestDto);
	public SuccessResponse deleteProductInCart(String id);
}
