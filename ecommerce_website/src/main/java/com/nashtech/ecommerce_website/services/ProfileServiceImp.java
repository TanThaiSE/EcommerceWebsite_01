package com.nashtech.ecommerce_website.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.ProfileRequest;
import com.nashtech.ecommerce_website.dto.request.ProfileUpdateInfoRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Profiles;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.repository.AccountsRepository;
import com.nashtech.ecommerce_website.repository.ProfileRepository;

@Service
public class ProfileServiceImp implements ProfileService{
	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	AccountsRepository accountsRepository;
	
	
	@Override
	public SuccessResponse addToProfile(ProfileRequest profileRequest) {
		try {
			String idProfile = UUID.randomUUID().toString();
			profileRequest.setId(idProfile);
			profileRequest.setName("Unknow");
			profileRequest.setSex(0);
			profileRequest.setBirth(new Date());
			profileRequest.setAddress("Unknow");
			profileRepository.addToProfile(profileRequest);
			SuccessResponse result = new SuccessResponse("201", "add profile success", profileRequest);
			return result;
		} catch (Exception e) {
			throw new SqlException("Profile cannot insert");
		}
	}



	@Override
	public SuccessResponse getAllUsers(int page,int offset) {
		Pageable pageable=PageRequest.of(page, offset);
		Page<Profiles> lstProfiles=profileRepository.findAllByOrderByAccountsProfiles_CreatedDateDesc(pageable);
		if(lstProfiles.getContent().size()>0) {
			Map<String,Object> res=new HashMap<>();
			res.put("listUser", lstProfiles.getContent());
			res.put("totalPage",lstProfiles.getTotalPages());
			return new SuccessResponse("200","get all users success",res);
		}
		throw new NotFoundException("Not found users");
	}



	@Override
	public SuccessResponse showDetailProfile(String accountId) {
		Optional<Profiles> detail=profileRepository.findAllByAccountsProfiles_Id(accountId);
		if(detail.isEmpty()) {
			throw new NotFoundException("Not found account");
		}
		return new SuccessResponse("202","get detail users success",detail.get());
	}



	@Override
	public SuccessResponse updateBlockedUsers(String accountId) {
		Optional<Profiles> detail=profileRepository.findAllByAccountsProfiles_Id(accountId);
		if(detail.isEmpty()) {
			throw new NotFoundException("Not found account");
		}
		int oldIsBlocked=detail.get().getAccountsProfiles().getIsBlocked();
		int newBlocked= (oldIsBlocked==0?1:0);
		accountsRepository.updateBlocked(accountId, newBlocked);
		Profiles res=profileRepository.findAllByAccountsProfiles_Id(accountId).get();
		return new SuccessResponse("202","update user success",res);
	}



	@Override
	public SuccessResponse updateInfoUser(ProfileUpdateInfoRequest profileUpdateInfoRequest) {
		Optional<Profiles> detail=profileRepository.findAllByAccountsProfiles_Id(profileUpdateInfoRequest.getAccountId());
		if(detail.isEmpty()) {
			throw new NotFoundException("Not found account");
		}
		profileRepository.updateProfile(profileUpdateInfoRequest);
		return new SuccessResponse("202","update user success",profileUpdateInfoRequest);
	}

}
