package com.nashtech.ecommerce_website.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.ProfileRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.repository.ProfileRepository;

@Service
public class ProfileServiceImp implements ProfileService{
	@Autowired
	ProfileRepository profileRepository;
	
	@Override
	public SuccessResponse addToProfile(ProfileRequest profileRequest) {
		try {
			String idProfile = UUID.randomUUID().toString();
			profileRequest.setId(idProfile);
			profileRequest.setName("");
			profileRequest.setSex(0);
			profileRequest.setBirth(new Date());
			profileRequest.setAddress("");
			profileRepository.addToProfile(profileRequest);
			SuccessResponse result = new SuccessResponse("201", "add profile success", profileRequest);
			return result;
		} catch (Exception e) {
			throw new SqlException("Profile cannot insert");
		}
	}

}
