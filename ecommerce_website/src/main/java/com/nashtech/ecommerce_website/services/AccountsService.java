package com.nashtech.ecommerce_website.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.entity.Accounts;
import com.nashtech.ecommerce_website.repository.AccountsRepository;

@Service
public class AccountsService implements AccountsServiceImp {
	@Autowired
	AccountsRepository accountsRepository;

	@Override
	public Map<String,Object> findByemail(String email) {
		
		return accountsRepository.findByemail(email);
	}

}
