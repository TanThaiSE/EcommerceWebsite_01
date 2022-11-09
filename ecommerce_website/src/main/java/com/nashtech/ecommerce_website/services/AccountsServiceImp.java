package com.nashtech.ecommerce_website.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.AccountCreateRequest;
import com.nashtech.ecommerce_website.dto.request.AccountUpdatePasswordRequest;
import com.nashtech.ecommerce_website.dto.request.NewAccountRequest;
import com.nashtech.ecommerce_website.dto.request.RegisterRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Accounts;
import com.nashtech.ecommerce_website.exceptions.ItemExistException;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
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
	public SuccessResponse createNewAccount(AccountCreateRequest accountCreateRequest) {
		
		List<Accounts> isExistAccount=accountsRepository.findByEmailOrPhone(accountCreateRequest.getEmail(),accountCreateRequest.getPhone());
		if(isExistAccount.size()>0) {
			SuccessResponse result = new SuccessResponse("302", "account is existed",accountCreateRequest);
			return result;
		}
		String idAccount = UUID.randomUUID().toString();
		accountCreateRequest.setIdAccount(idAccount);
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		accountCreateRequest.setPassword(encoder.encode(accountCreateRequest.getPassword()));
		accountCreateRequest.setRoleId("218852d4-6eb3-4039-b193-c0b36da48d57");
		accountCreateRequest.setIsBlocked(0);
		accountCreateRequest.setCreatedDate(new Date());
		String idProfile=UUID.randomUUID().toString();
		accountCreateRequest.setIdProfile(idProfile);
		int ac=accountsRepository.addNewAccount(accountCreateRequest);
		if(ac>0) {
			profileRepository.addNewProfile(accountCreateRequest);
		}
		SuccessResponse result = new SuccessResponse("202", "create account success",accountCreateRequest);
		return result;
	}

	@Override
	public SuccessResponse updatePassword(AccountUpdatePasswordRequest accountUpdatePasswordRequest) {
		Optional<Accounts> acc=accountsRepository.findById(accountUpdatePasswordRequest.getId());
		if(acc.isEmpty()) {
			throw new NotFoundException("Not found account");
		}
		
		BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
		boolean isMatch=bc.matches(accountUpdatePasswordRequest.getOldPassword(),acc.get().getPassword());
		
		if(!isMatch) {
			throw new NotFoundException("Old password is not correct");
		}
		accountUpdatePasswordRequest.setNewPassword(bc.encode(accountUpdatePasswordRequest.getNewPassword()));
		accountsRepository.updatePassword(accountUpdatePasswordRequest.getId(), accountUpdatePasswordRequest.getNewPassword());
		return new SuccessResponse("202","update password success",accountUpdatePasswordRequest);
	}
	
}
