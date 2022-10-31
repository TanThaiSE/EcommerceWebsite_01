package com.nashtech.ecommerce_website.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.NewAccountRequest;
import com.nashtech.ecommerce_website.dto.request.RegisterRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Accounts;
import com.nashtech.ecommerce_website.exceptions.ItemExistException;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.repository.AccountsRepository;
import com.nashtech.ecommerce_website.repository.ProfileRepository;

@Service
public class AccountsServiceImp implements AccountsService{
	@Autowired
	AccountsRepository accountsRepository;
	@Autowired
	ProfileRepository profileRepository;

	public AccountsServiceImp(AccountsRepository accountsRepository) {
		this.accountsRepository = accountsRepository;
	}

	@Override
	public Map<String,Object> findByPhone(String phone) {
		
		return accountsRepository.findByPhone(phone);
	}

	@Override
	public SuccessResponse addToAccount(RegisterRequest registerRequest) {
		List<Accounts> isExistAccount=accountsRepository.findByEmailOrPhone(registerRequest.getEmail(),registerRequest.getPhone());
		if(isExistAccount.size()>0) {
			SuccessResponse result = new SuccessResponse("302", "account is existed",registerRequest);
			return result;
		}
		else {
			String idAccount = UUID.randomUUID().toString();
			registerRequest.setId(idAccount);
			BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
			registerRequest.setPassword(encoder.encode(registerRequest.getPassword()));
			registerRequest.setRoleId("218852d4-6eb3-4039-b193-c0b36da48d57");
			registerRequest.setIsBlocked(0);
			registerRequest.setCreatedDate(new Date());
			accountsRepository.registerAccount(registerRequest);
			SuccessResponse result = new SuccessResponse("201", "add account success",registerRequest);
			return result;
		}
	}

	@Override
	public SuccessResponse createNewAccount(NewAccountRequest newAccountRequest) {
		List<Accounts> isExistAccount=accountsRepository.findByEmailOrPhone(newAccountRequest.getEmail(),newAccountRequest.getPhone());
		if(isExistAccount.size()>0) {
			SuccessResponse result = new SuccessResponse("302", "account is existed",newAccountRequest);
			return result;
		}
		String idAccount = UUID.randomUUID().toString();
		newAccountRequest.setIdAccount(idAccount);
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		newAccountRequest.setPassword(encoder.encode(newAccountRequest.getPassword()));
		newAccountRequest.setCreatedDate(new Date());
		String idProfile=UUID.randomUUID().toString();
		newAccountRequest.setIdProfile(idProfile);
		int ac=accountsRepository.addNewAccount(newAccountRequest);
		if(ac>0) {
			profileRepository.addNewProfile(newAccountRequest);
		}
		SuccessResponse result = new SuccessResponse("200", "create account success",newAccountRequest);
		return result;
	}
	
}
