package com.nashtech.ecommerce_website.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.ImageAddRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.pojo.ImageAddProductPojo;
import com.nashtech.ecommerce_website.repository.ImagesRepository;

@Service
public class ImageServiceImp implements ImageService {
	@Autowired
	ImagesRepository imagesRepository;
	
	@Override
	public SuccessResponse createImageProduct(ImageAddRequest imageAddRequest) {
		try {
			for(ImageAddProductPojo o:imageAddRequest.getListImages()) {
				String id=UUID.randomUUID().toString();
				o.setId(id);
				imagesRepository.createNewImage(o);
			}
			return new SuccessResponse("201","createImageProduct success", imageAddRequest);
		} catch (Exception e) {
			throw new SqlException("Cannot insert image product");
		}
	}
	
}
