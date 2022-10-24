package com.nashtech.ecommerce_website.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.RatingAddRequest;
import com.nashtech.ecommerce_website.dto.response.RateResponse;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.exceptions.ItemExistException;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.repository.RatingRepository;

@Service
public class RatingServiceImp implements RatingService {
	@Autowired
	RatingRepository ratingRepository;

	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public SuccessResponse addRatingProduct(RatingAddRequest ratingAddRequest) {
		Map<String, Object> isRating = ratingRepository.findRatingWithOrderDetailId(ratingAddRequest.getOrderDetailId());
		if (isRating.isEmpty() || isRating == null) {
			try {
				String idRating = UUID.randomUUID().toString();
				ratingAddRequest.setId(idRating);
				ratingAddRequest.setRatingDate(new Date());
				ratingRepository.addRatingToProduct(ratingAddRequest);
				SuccessResponse result = new SuccessResponse("201", "add rating success", ratingAddRequest);
				return result;
			} catch (Exception e) {
				throw new SqlException("cannot add rating"+e.getMessage());
			}
		}
		throw new ItemExistException("product is rated");

	}

	@Override
	public List<RateResponse> getRatingAndCommentProduct(String productId) {
		try {
			List<Map<String,Object>> lst=ratingRepository.getRatingAndCommentProduct(productId);
			if(lst.size()>0) {
				List<RateResponse> lstRateRes=new ArrayList<>();
				for(Map<String,Object> o:lst) {
					lstRateRes.add(modelMapper.map(o, RateResponse.class));
				}
				return lstRateRes;
			}
			else {
				throw new NotFoundException("Cannot found rate and product:");
			}
		} catch (Exception e) {
			throw new SqlException("Cannot get comment and rating: "+e.getMessage());
		}
		
	}

}
