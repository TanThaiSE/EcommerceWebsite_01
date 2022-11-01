package com.nashtech.ecommerce_website.services;

import java.util.List;

import com.nashtech.ecommerce_website.dto.request.ProfileRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Profiles;

public interface ProfileService {
	public SuccessResponse getAllUsers(int page,int offset);
	
	public SuccessResponse addToProfile(ProfileRequest profileRequest);
	
	public SuccessResponse showDetailProfile(String accountId);
	
	public SuccessResponse updateBlockedUsers(String accountId);
}
