package com.nashtech.ecommerce_website.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.dto.request.RatingAddRequest;
import com.nashtech.ecommerce_website.dto.response.RateResponse;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.RatingServiceImp;

@RestController
@RequestMapping("/api/v1/rating")
public class RatingController {
	@Autowired
	RatingServiceImp ratingServiceImp;
	
	@PostMapping("")
	public SuccessResponse addRatingProduct(@Valid @RequestBody RatingAddRequest ratingAddRequest) {
		return ratingServiceImp.addRatingProduct(ratingAddRequest);
	}
	
	@GetMapping("/{idProduct}")
	public List<RateResponse> getRatingAndCommentProduct(@PathVariable("idProduct")String idProduct){
		return ratingServiceImp.getRatingAndCommentProduct(idProduct);
	}
}
