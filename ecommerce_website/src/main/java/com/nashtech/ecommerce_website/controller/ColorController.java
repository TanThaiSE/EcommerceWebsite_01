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
import com.nashtech.ecommerce_website.services.ColorProductServiceImp;
import com.nashtech.ecommerce_website.services.ColorServiceImp;

@RestController
@RequestMapping("/api/v1/color")
public class ColorController {
	@Autowired
	ColorServiceImp colorServiceImp;
	@Autowired
	ColorProductServiceImp colorProductServiceImp;
	@GetMapping("")
	public SuccessResponse getAllColor() {
		return colorServiceImp.getAllColor();
	}
	@PostMapping("")
	public SuccessResponse createColorForProduct(@Valid @RequestBody AttributeAddRequest addRequest) {
		return colorProductServiceImp.createColorProduct(addRequest);
	}
	@DeleteMapping("/{idProduct}/product")
	public SuccessResponse deleteColorProduct(@PathVariable("idProduct")String idProduct) {
		return colorProductServiceImp.deleteColorProductByProductId(idProduct);
	}
}
