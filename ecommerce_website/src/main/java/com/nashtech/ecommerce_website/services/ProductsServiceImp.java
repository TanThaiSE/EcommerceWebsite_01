package com.nashtech.ecommerce_website.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.ProductCreateRequest;
import com.nashtech.ecommerce_website.dto.response.ProductDetailResponseDto;
import com.nashtech.ecommerce_website.dto.response.ProductUpdateResponse;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.pojo.AttributeProductPojo;
import com.nashtech.ecommerce_website.pojo.DetailProductPojo;
import com.nashtech.ecommerce_website.pojo.ImageProductPojo;
import com.nashtech.ecommerce_website.pojo.ListUpdateProductPojo;

import com.nashtech.ecommerce_website.repository.ColorProductRepository;
import com.nashtech.ecommerce_website.repository.ImagesRepository;
import com.nashtech.ecommerce_website.repository.ProductsRepository;
import com.nashtech.ecommerce_website.repository.SizeProductRepository;

@Service
public class ProductsServiceImp implements ProductsService {
	@Autowired
	ProductsRepository productsRepository;
	@Autowired
	ColorProductRepository colorProductRepository;
	@Autowired
	SizeProductRepository sizeProductRepository;
	@Autowired
	ImagesRepository imagesRepository;

	public ProductsServiceImp(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}

	@Override
	public ProductDetailResponseDto getDetailProduct(String productId) {
		Optional<DetailProductPojo> detailProduct = productsRepository.getDetailProduct(productId);
		if (detailProduct.isEmpty()) {
			throw new NotFoundException("Not found detailproduct");
		}
		List<AttributeProductPojo> getColorProduct = productsRepository.getColorProduct(productId);
		List<AttributeProductPojo> getSizeProduct = productsRepository.getSizeProduct(productId);
		List<ImageProductPojo> getImageProduct = productsRepository.getImageProduct(productId);

		ProductDetailResponseDto result = new ProductDetailResponseDto(detailProduct.get(), getColorProduct,
				getSizeProduct, getImageProduct);

		return result;
	}

	@Override
	public ListUpdateProductPojo updateNumberBuyProduct(ListUpdateProductPojo productRequest) {
		for (ProductUpdateResponse p : productRequest.getPrepareToUpdate()) 
		{
			Optional<DetailProductPojo> detailProduct = productsRepository.getDetailProduct(p.getProductId());
			if (detailProduct.isEmpty())
			{
				throw new NotFoundException("Not found detailproduct");
			}
			DetailProductPojo detailProductPojo = detailProduct.get();
			try {
				int isUpdate = productsRepository.updateNumberBuyProduct(p.getProductId(),p.getQuantity() + detailProductPojo.getNumber_buy());
			} catch (Exception e) {
				// TODO: handle exception
				throw new SqlException("Cannot update quantity product in cart");
			}

		}
		return productRequest;
	}

	@Override
	public SuccessResponse createNewProduct(ProductCreateRequest productCreateRequest) {
		String idProduct=UUID.randomUUID().toString();
		productCreateRequest.setId(idProduct);
		productCreateRequest.setCreatedDate(new Date());
		productCreateRequest.setUpdateDate(new Date());
		productCreateRequest.setRate(5);
		productCreateRequest.setNumberBuy(0);
		try {
			productsRepository.createNewProduct(idProduct, productCreateRequest);
			return new SuccessResponse("201","Create product success", productCreateRequest);
		} catch (Exception e) {
			throw new SqlException("Cannot insert to product");
		}

	}
	
	
}
