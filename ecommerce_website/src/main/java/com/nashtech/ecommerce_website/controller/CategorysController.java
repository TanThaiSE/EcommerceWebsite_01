package com.nashtech.ecommerce_website.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.services.CategorysService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/category")
public class CategorysController {
	@Autowired
	CategorysService categorysService;
	
	@GetMapping("")
	public ResponseEntity<?> getAllCategory(){
		return new ResponseEntity<List<Map<String,?>>>(categorysService.getAllCategory(),HttpStatus.OK);
	}
}
