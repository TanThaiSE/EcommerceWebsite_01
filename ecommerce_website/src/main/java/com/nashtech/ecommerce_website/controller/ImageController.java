package com.nashtech.ecommerce_website.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.ImageAddRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.ImageServiceImp;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
	@Autowired
	ImageServiceImp imageServiceImp;
	@PostMapping("")
	public SuccessResponse createImageForProduct(@Valid @RequestBody ImageAddRequest imageAddRequest) {
		return imageServiceImp.createImageProduct(imageAddRequest);
	}
	
	@DeleteMapping("/{idProduct}/product")
	public SuccessResponse deleteImageProduct(@PathVariable("idProduct")String idProduct) {
		return imageServiceImp.deleteImageByProductId(idProduct);
	}
}
