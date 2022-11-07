package com.nashtech.ecommerce_website.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.AttributeAddRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.pojo.AttributeAddProductPojo;
import com.nashtech.ecommerce_website.repository.ColorProductRepository;

@Service
public class ColorProductServiceImp implements ColorProductService{
	@Autowired
	ColorProductRepository colorProductRepository;

	@Override
	public SuccessResponse createColorProduct(AttributeAddRequest addRequest) {
		try {
			String idProduct=addRequest.getProductId();
			for(AttributeAddProductPojo o:addRequest.getListAttributes()) {
				String id=UUID.randomUUID().toString();
				o.setAttributeId(id);
				o.setProductId(idProduct);
				colorProductRepository.createNewColorProduct(o);
			}
			return new SuccessResponse("201","createColorProduct success", addRequest);
		} catch (Exception e) {
			throw new SqlException("Cannot insert color product");
		}

	}

	@Override
	public SuccessResponse deleteColorProductByProductId(String productId) {
		try {
			colorProductRepository.deleteAllByProductsColorsProducts_Id(productId);
			return new SuccessResponse("202","delete color product success", productId);
		} catch (Exception e) {
			throw new SqlException("Cannot delete color product");
		}
	}


	

}
