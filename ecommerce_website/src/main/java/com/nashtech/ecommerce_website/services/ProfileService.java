package com.nashtech.ecommerce_website.services;

import com.nashtech.ecommerce_website.dto.request.ProfileRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;

public interface ProfileService {
	public SuccessResponse addToProfile(ProfileRequest profileRequest);
}
