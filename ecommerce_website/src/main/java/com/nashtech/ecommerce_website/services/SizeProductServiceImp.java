package com.nashtech.ecommerce_website.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.AttributeAddRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.pojo.AttributeAddProductPojo;
import com.nashtech.ecommerce_website.repository.SizeProductRepository;

@Service
public class SizeProductServiceImp implements SizeProductService {
	@Autowired
	SizeProductRepository sizeProductRepository;
	@Override
	public SuccessResponse createSizeProduct(AttributeAddRequest addRequest) {
		try {
			String idProduct=addRequest.getProductId();
			for(AttributeAddProductPojo o:addRequest.getListAttributes()) {
				String id=UUID.randomUUID().toString();
				o.setAttributeId(id);
				o.setProductId(idProduct);
				sizeProductRepository.createNewSizeProduct(o);
			}
			return new SuccessResponse("201","createSizeProduct success", addRequest);
		} catch (Exception e) {
			throw new SqlException("Cannot insert Size product");
		}
	}
	@Override
	public SuccessResponse deleteSizeProductByProductId(String productId) {
		try {
			sizeProductRepository.deleteAllByProductsSizeProducts_Id(productId);
			return new SuccessResponse("202","delete size product success", productId);
		} catch (Exception e) {
			throw new SqlException("Cannot delete size product");
		}
	}

}
