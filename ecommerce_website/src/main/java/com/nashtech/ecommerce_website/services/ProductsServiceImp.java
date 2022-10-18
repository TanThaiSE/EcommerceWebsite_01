package com.nashtech.ecommerce_website.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.response.ProductDetailResponseDto;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.pojo.AttributeProductPojo;
import com.nashtech.ecommerce_website.pojo.DetailProductPojo;
import com.nashtech.ecommerce_website.pojo.ImageProductPojo;
import com.nashtech.ecommerce_website.repository.ProductsRepository;

@Service
public class ProductsServiceImp implements ProductsService {
	@Autowired
	ProductsRepository productsRepository;

	@Override
	public ProductDetailResponseDto getDetailProduct(String productId) {
		Optional<DetailProductPojo> detailProduct=productsRepository.getDetailProduct(productId);
		if(detailProduct.isEmpty()) {
			throw new NotFoundException("Not found detailproduct");
		}
		List<AttributeProductPojo> getColorProduct=productsRepository.getColorProduct(productId);
		List<AttributeProductPojo> getSizeProduct=productsRepository.getSizeProduct(productId);
		List<ImageProductPojo> getImageProduct=productsRepository.getImageProduct(productId);
		
		ProductDetailResponseDto result=new ProductDetailResponseDto(detailProduct.get(), getColorProduct, getSizeProduct, getImageProduct);

		return result;
	}
	
}
