package com.nashtech.ecommerce_website.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nashtech.ecommerce_website.dto.response.ProductDetailResponseDto;
import com.nashtech.ecommerce_website.pojo.ListUpdateProductPojo;
import com.nashtech.ecommerce_website.services.ProductsServiceImp;


@RestController
@RequestMapping("/api/v1/product")
public class ProductsController {
	@Autowired
	ProductsServiceImp productsServiceImp;

	@GetMapping("/{idProduct}")
	public ProductDetailResponseDto getDetailProduct(@PathVariable("idProduct") String idProduct){
		return productsServiceImp.getDetailProduct(idProduct);
	}
	
	@PutMapping("")
	public ListUpdateProductPojo updateNumberBuyListProduct(@Valid @RequestBody ListUpdateProductPojo listUpdateProductPojo) {
		return productsServiceImp.updateNumberBuyProduct(listUpdateProductPojo);
	}
	
}
