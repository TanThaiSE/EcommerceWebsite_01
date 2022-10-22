package com.nashtech.ecommerce_website.services;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.RatingAddRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.exceptions.ItemExistException;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.repository.RatingRepository;

@Service
public class RatingServiceImp implements RatingService {
	@Autowired
	RatingRepository ratingRepository;

	@Override
	public SuccessResponse addRatingProduct(RatingAddRequest ratingAddRequest) {
		Map<String, Object> isRating = ratingRepository.findRatingWithOrderDetailId(ratingAddRequest.getOrderDetailId());
		if (isRating.isEmpty() || isRating == null) {
			try {
				String idRating = UUID.randomUUID().toString();
				ratingAddRequest.setId(idRating);
				ratingRepository.addRatingToProduct(ratingAddRequest);
				SuccessResponse result = new SuccessResponse("201", "add rating success", ratingAddRequest);
				return result;
			} catch (Exception e) {
				throw new SqlException("cannot add rating");
			}
		}
		throw new ItemExistException("product is rated");

	}

}
