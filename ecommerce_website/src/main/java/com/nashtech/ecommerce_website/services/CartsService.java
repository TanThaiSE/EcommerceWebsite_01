package com.nashtech.ecommerce_website.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.response.CartsResponse;
import com.nashtech.ecommerce_website.dto.response.FindProductInCartResponseDto;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Carts;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.repository.CartsRepository;

@Service
public class CartsService implements CartsServiceImp {
	@Autowired
	CartsRepository cartsRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<Carts> abc() {
		// TODO Auto-generated method stub
		return cartsRepository.findAll();
	}
	
	@Override
	public SuccessResponse addToCart(CartsRequestDto cartsRequestDto) {		
		String accountId = "94288321-4c0a-404f-a0a9-c40ab7095602";
		Map<String, Object> findProduct = cartsRepository.findProductInCart(cartsRequestDto,accountId);
		if (findProduct==null ||findProduct.isEmpty()) {
			try {
				cartsRepository.addToCart(cartsRequestDto,accountId);
				SuccessResponse successResponse=new SuccessResponse("200","insert product success");
				return successResponse;
			} catch (Exception e) {
				throw new SqlException("Cannot insert product to cart");
			}
		}
		else {
			try {
				int quantity=(int) findProduct.get("quantity");
				int quantityNew=quantity+cartsRequestDto.getQuantity();
				cartsRepository.updateQuantityCart((String)findProduct.get("id"),quantityNew);
				SuccessResponse successResponse=new SuccessResponse("200","update product success");
				return successResponse;
			} catch (Exception e) {
				System.out.println(e);
				throw new SqlException(e.getMessage());
			}
		}
		
	}



//
//	@Override
//	public void updateCart(Carts carts) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteCart(String id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void getCart(String accountId) {
//		// TODO Auto-generated method stub
//		
//	}

}
