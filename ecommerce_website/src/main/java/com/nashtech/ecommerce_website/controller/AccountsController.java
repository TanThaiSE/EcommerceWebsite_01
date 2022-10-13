package com.nashtech.ecommerce_website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.entity.Accounts;
import com.nashtech.ecommerce_website.services.AccountsService;

@RestController
@RequestMapping("/test")
public class AccountsController {
	@Autowired
	AccountsService accountsService;
	
//	@GetMapping("/allabc")
//	public ResponseEntity<?> abc(){
//		return new ResponseEntity<List<Accounts>>(accountsService.findAllAccount(),HttpStatus.OK);
//	}
}
