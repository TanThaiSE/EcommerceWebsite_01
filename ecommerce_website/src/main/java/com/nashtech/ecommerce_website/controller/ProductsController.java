package com.nashtech.ecommerce_website.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce_website.services.ProductsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/product")
public class ProductsController {
	@Autowired
	ProductsService productsService;
	
	@GetMapping("/category/{idCategory}")
	public ResponseEntity<?>getAllProductByCategory(@PathVariable("idCategory") String idCategory ){
		return new ResponseEntity<List<Map<String,?>>>(productsService.getAllProductByCategory(idCategory),HttpStatus.OK);
	}
	
	@GetMapping("/detail-product/{idProduct}")
	public ResponseEntity<?>getDetailProduct(@PathVariable("idProduct") String idProduct){
		return new ResponseEntity<Map<String,Object>>(productsService.getDetailProduct(idProduct),HttpStatus.OK);
	}
}
