package com.nashtech.ecommerce_website.services;

import java.util.List;

import com.nashtech.ecommerce_website.dto.request.RatingAddRequest;
import com.nashtech.ecommerce_website.dto.response.RateResponse;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;

public interface RatingService {
	public SuccessResponse addRatingProduct(RatingAddRequest ratingAddRequest);
	
	public List<RateResponse> getRatingAndCommentProduct(String productId);
}
