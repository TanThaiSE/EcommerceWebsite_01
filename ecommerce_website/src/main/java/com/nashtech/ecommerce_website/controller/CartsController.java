package com.nashtech.ecommerce_website.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nashtech.ecommerce_website.dto.request.CartUpdateQuantityRequest;
import com.nashtech.ecommerce_website.dto.request.CartsDeleteRequest;
import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.response.CartResponseDto;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.CartsServiceImp;


@RestController
@RequestMapping("/api/v1/cart")
public class CartsController {
	@Autowired
	CartsServiceImp cartsServiceImp;
	
	@GetMapping("/{accountId}")
	public List<CartResponseDto> getAllProductInCart(@PathVariable("accountId") String accountId){
		return  cartsServiceImp.getAllProductInCartByAccountId(accountId);
	}
	
	@PostMapping("")
	public SuccessResponse addToCart(@Valid @RequestBody CartsRequestDto cartsRequestDto ){
		return cartsServiceImp.addToCart(cartsRequestDto);
	}
	
	@PutMapping("/{id}/product")
	public CartResponseDto updateQuantityProductInCart(@PathVariable("id") String id ,@Valid @RequestBody CartUpdateQuantityRequest cartUpdateQuantityRequest ) {
		return cartsServiceImp.updateQuantityProductInCart(id,cartUpdateQuantityRequest);
	}		
	
	@DeleteMapping("/{idCart}/{accountId}/account")
	public SuccessResponse deleteProductInCart(@PathVariable("idCart")String id,@PathVariable("accountId")String idAccount) {
		return cartsServiceImp.deleteProductInCart(id,idAccount);
	}
	
	@DeleteMapping("")
	public SuccessResponse deleteMutipleProductInCart(@Valid @RequestBody CartsDeleteRequest cartsDeleteRequest) {
		return cartsServiceImp.deleteMutipleProductInCart(cartsDeleteRequest);
	}
}

/*viết unit test=>service, controller*/
