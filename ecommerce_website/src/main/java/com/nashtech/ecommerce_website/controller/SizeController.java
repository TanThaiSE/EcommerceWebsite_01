package com.nashtech.ecommerce_website.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.AttributeAddRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.SizeProductServiceImp;
import com.nashtech.ecommerce_website.services.SizeServiceImp;

@RestController
@RequestMapping("/api/v1/size")
public class SizeController {
	@Autowired
	SizeServiceImp sizeServiceImp;
	@Autowired
	SizeProductServiceImp sizeProductServiceImp;
	@GetMapping("")
	public SuccessResponse getAllSize() {
		return sizeServiceImp.getAllSize();
	}
	@PostMapping("")
	public SuccessResponse createSizeForProduct(@Valid @RequestBody AttributeAddRequest addRequest) {
		return sizeProductServiceImp.createSizeProduct(addRequest);
	}
	@DeleteMapping("/{idProduct}/product")
	public SuccessResponse deleteSizeProduct(@PathVariable("idProduct")String idProduct) {
		return sizeProductServiceImp.deleteSizeProductByProductId(idProduct);
	}
}
