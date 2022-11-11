package com.nashtech.ecommerce_website.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.CartUpdateQuantityRequest;
import com.nashtech.ecommerce_website.dto.request.CartsDeleteRequest;
import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.response.CartResponseDto;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Carts;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.helper.JwtAuthFilter;
import com.nashtech.ecommerce_website.helper.JwtProvider;
import com.nashtech.ecommerce_website.pojo.CartDeletePojo;
import com.nashtech.ecommerce_website.repository.CartsRepository;

@Service
public class CartsServiceImp implements CartsService {
	@Autowired
	CartsRepository cartsRepository;
	

	
	@Autowired 
	JwtAuthFilter jwtAuthFilter;
	
	@Autowired
	JwtProvider jwtProvider;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public SuccessResponse addToCart(CartsRequestDto cartsRequestDto) {
		String accountId=cartsRequestDto.getAccountId();
		Map<String, Object> isValidValue = cartsRepository.findProductWithSizeAndColor(cartsRequestDto);
		if (isValidValue == null || isValidValue.isEmpty()) {
			throw new NotFoundException("Values are not correct");
		} else {
			Map<String, Object> findProduct = cartsRepository.findProductInCart(cartsRequestDto, accountId);
			if (findProduct == null || findProduct.isEmpty()) {
				try {
					String idCart = UUID.randomUUID().toString();
					cartsRequestDto.setAccountId(accountId);
					cartsRequestDto.setId(idCart);
					cartsRepository.addToCart(cartsRequestDto, accountId);
					CartResponseDto cartResponseDto = modelMapper.map(cartsRequestDto, CartResponseDto.class);
					SuccessResponse result = new SuccessResponse("201", "add product to cart success", cartResponseDto);
					return result;
				} catch (Exception e) {
					throw new SqlException("Product cannot insert");
				}
			} else {
				CartResponseDto cartResponseDto = modelMapper.map(findProduct, CartResponseDto.class);
				SuccessResponse result = new SuccessResponse("302", "Product existed in cart", cartResponseDto);
				return result;
			}
		}

	}

	@Override
	public List<CartResponseDto> getAllProductInCartByAccountId(String accountId) {

		List<Map<String, Object>> getProduct = cartsRepository.getAllProductInCartByAccountId(accountId);
		if (getProduct.size()==0 || getProduct == null) {
			throw new NotFoundException("Not found product in cart");
		} else {
			List<CartResponseDto> lst = new ArrayList<CartResponseDto>();
			for (Map<String, Object> x : getProduct) {
				lst.add(modelMapper.map(x, CartResponseDto.class));
			}
			return lst;
		}

	}

	@Override
	public CartResponseDto updateQuantityProductInCart(String id, CartUpdateQuantityRequest cartUpdateQuantityRequest) {
		String accountId=cartUpdateQuantityRequest.getAccountId();
		try {
			int isUpdate = cartsRepository.updateQuantityCart(id, cartUpdateQuantityRequest, accountId);
			if (isUpdate == 1) {
				CartResponseDto cartResponseDto = modelMapper.map(cartUpdateQuantityRequest, CartResponseDto.class);
				cartResponseDto.setId(id);
				return cartResponseDto;
			} else {
				throw new SqlException("Cannot update quantity product in cart");
			}

		} catch (Exception e) {
			throw new SqlException("Cannot update quantity product in cart");

		}
	}
	
	@Override
	public SuccessResponse deleteProductInCart(String id,String accountId) {
		try {
			Optional<Carts> c = cartsRepository.findByIdAndAccountCart_Id(id, accountId);
			if (c.isEmpty()) {
				throw new NotFoundException("Not found product in cart");
			} else {
				cartsRepository.deleteById(id);
				SuccessResponse successResponse = new SuccessResponse("200", "delete product success", null);
				return successResponse;
			}
		} catch (Exception e) {
			throw new SqlException("Cannot delete product in cart "+e.getMessage());
		}
	}

	@Override
	public SuccessResponse deleteMutipleProductInCart(CartsDeleteRequest listProductCart) {
		List<CartDeletePojo> lstProduct=listProductCart.getPrepareToDelete();
		if(lstProduct.size()>0) {
			for(CartDeletePojo c:lstProduct) {
				cartsRepository.deleteById(c.getId());
			}
			SuccessResponse successResponse = new SuccessResponse("200", "delete product success", null);
			return successResponse;
		}
		else {
			throw new NotFoundException("Not found product in cart");
		}
		
	}
	
}
