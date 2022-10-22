package com.nashtech.ecommerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.RatingAddRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.RatingServiceImp;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/rating")
public class RatingController {
	@Autowired
	RatingServiceImp ratingServiceImp;
	
	@PostMapping("")
	public SuccessResponse addRatingProduct(RatingAddRequest ratingAddRequest) {
		return ratingServiceImp.addRatingProduct(ratingAddRequest);
	}
}
