package com.nashtech.ecommerce_website.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.RegisterRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Accounts;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.repository.AccountsRepository;

@Service
public class AccountsServiceImp implements AccountsService{
	@Autowired
	AccountsRepository accountsRepository;

	@Override
	public Map<String,Object> findByPhone(String phone) {
		
		return accountsRepository.findByPhone(phone);
	}

	@Override
	public SuccessResponse AddToAccount(RegisterRequest registerRequest) {
		try {
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
				accountsRepository.registerAccount(registerRequest);
				SuccessResponse result = new SuccessResponse("201", "add account success",registerRequest);
				return result;
			}
			
		} catch (Exception e) {
			throw new SqlException("Cannot register");
		}

	}
	
}
