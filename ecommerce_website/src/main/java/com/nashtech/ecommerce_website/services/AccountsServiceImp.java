package com.nashtech.ecommerce_website.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.repository.AccountsRepository;

@Service
public class AccountsServiceImp implements AccountsService{
	@Autowired
	AccountsRepository accountsRepository;

	@Override
	public Map<String,Object> findByPhone(String phone) {
		
		return accountsRepository.findByPhone(phone);
	}
	
}
