package com.nashtech.ecommerce_website.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtech.ecommerce_website.dto.request.CartUpdateQuantityRequest;
import com.nashtech.ecommerce_website.dto.request.CartsDeleteRequest;
import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.response.CartResponseDto;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.pojo.CartDeletePojo;
import com.nashtech.ecommerce_website.services.CartsServiceImp;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CartsControllerTest {
	@MockBean
	CartsServiceImp cartsServiceImp;
	@Autowired
	private MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();
	CartResponseDto cartResponseDto;
	String idAccount;
	@BeforeEach
	void beforeEach() {
		idAccount="1";
		cartResponseDto=new CartResponseDto();
		cartResponseDto.setId("1");
		cartResponseDto.setProductId("1");
		cartResponseDto.setQuantity(1);
		cartResponseDto.setPrice(10);
		cartResponseDto.setSizeId("1");
		cartResponseDto.setColorId("1");
		cartResponseDto.setSizeName("S");
		cartResponseDto.setColorName("black");
		cartResponseDto.setImgName("abc.png");
		cartResponseDto.setProductName("abcde");
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void getAllProductInCart_ShouldReturnListCartResponseDto_WhenGetSuccess() throws Exception {
		List<CartResponseDto> lst=new ArrayList<>();
		lst.add(cartResponseDto);
		when(cartsServiceImp.getAllProductInCartByAccountId(idAccount)).thenReturn(lst);
		mockMvc.perform(get("/api/v1/cart/{accountId}",idAccount)).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void getAllProductInCart_ShouldReturnNotFound_WhenNotFoundData() throws Exception {
		String idAc="2";
		when(cartsServiceImp.getAllProductInCartByAccountId(idAc)).thenThrow(NotFoundException.class);
		mockMvc.perform(get("/api/v1/cart/{accountId}",idAc)).andExpect(status().isNotFound());
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void addToCart_ShouldReturnSuccessResponse_WhenGetSuccess() throws Exception {
		CartsRequestDto cartsRequestDto=new CartsRequestDto();
		cartsRequestDto.setId("1");
		cartsRequestDto.setProductId("1");
		cartsRequestDto.setQuantity(1);
		cartsRequestDto.setPrice(10);
		cartsRequestDto.setAccountId("1");
		cartsRequestDto.setSizeId("1");
		cartsRequestDto.setColorId("1");
		SuccessResponse result = new SuccessResponse("201", "add product to cart success", cartsRequestDto);
		when(cartsServiceImp.addToCart(cartsRequestDto)).thenReturn(result);
		mockMvc.perform(post("/api/v1/cart").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(cartsRequestDto)))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void addToCart_ShouldReturnNotFound_WhenNotFoundData() throws JsonProcessingException, Exception {
		CartsRequestDto cartsRequestDto=new CartsRequestDto();
		cartsRequestDto.setId("12");
		cartsRequestDto.setProductId("1");
		cartsRequestDto.setQuantity(1);
		cartsRequestDto.setPrice(10);
		cartsRequestDto.setAccountId("1");
		cartsRequestDto.setSizeId("1");
		cartsRequestDto.setColorId("1");
		when(cartsServiceImp.addToCart(any())).thenThrow(NotFoundException.class);
		mockMvc.perform(post("/api/v1/cart").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(cartsRequestDto)))
				.andExpect(status().isNotFound());
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void updateQuantityProductInCart_ShouldReturnCartResponseDto_WhenGetSuccess() throws JsonProcessingException, Exception {
		CartUpdateQuantityRequest cartUpdateQuantityRequest=new CartUpdateQuantityRequest();
		cartUpdateQuantityRequest.setId("1");
		cartUpdateQuantityRequest.setProductId("1");
		cartUpdateQuantityRequest.setQuantity(1);
		cartUpdateQuantityRequest.setPrice(1);
		cartUpdateQuantityRequest.setAccountId("1");
		cartUpdateQuantityRequest.setSizeId("1");
		cartUpdateQuantityRequest.setColorId("1");
		String id="1";
		when(cartsServiceImp.updateQuantityProductInCart(id, cartUpdateQuantityRequest)).thenReturn(cartResponseDto);
		mockMvc.perform(put("/api/v1/cart/{id}/product",id).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(cartUpdateQuantityRequest)))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void updateQuantityProductInCart_ShouldReturnSqlException_WhenDataValid() throws JsonProcessingException, Exception {
		CartUpdateQuantityRequest cartUpdateQuantityRequest=new CartUpdateQuantityRequest();
		cartUpdateQuantityRequest.setProductId("1");
		cartUpdateQuantityRequest.setQuantity(1);
		cartUpdateQuantityRequest.setPrice(1);
		cartUpdateQuantityRequest.setAccountId("1");
		cartUpdateQuantityRequest.setSizeId("1");
		cartUpdateQuantityRequest.setColorId("1");
		String id="abc";
		when(cartsServiceImp.updateQuantityProductInCart(anyString(), any())).thenThrow(SqlException.class);
		mockMvc.perform(put("/api/v1/cart/{id}/product",id).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(cartUpdateQuantityRequest)))
				.andExpect(status().is(409));
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void deleteProductInCart_ShoudReturnSuccessResponse_WhenIdValid() throws Exception {
		SuccessResponse successResponse = new SuccessResponse("200", "delete product success", null);
		String id="1";
		when(cartsServiceImp.deleteProductInCart(id,idAccount)).thenReturn(successResponse);
		mockMvc.perform(delete("/api/v1/cart/{idCart}/{accountId}/account",id,idAccount)).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void deleteProductInCart_ShoudReturnNotFound_WhenIdInValid() throws Exception {
		String id="10";
		when(cartsServiceImp.deleteProductInCart(id,idAccount)).thenThrow(NotFoundException.class);
		mockMvc.perform(delete("/api/v1/cart/{idCart}/{accountId}/account",id,idAccount)).andExpect(status().isNotFound());
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void deleteMutipleProductInCart_ShoudReturnSuccessResponse_WhenDataValid() throws Exception {
		SuccessResponse successResponse = new SuccessResponse("200", "delete product success", null);
		CartDeletePojo cartDeletePojo=new CartDeletePojo();
		cartDeletePojo.setId("1");
		cartDeletePojo.setProductId("1");
		cartDeletePojo.setQuantity(10);
		cartDeletePojo.setPrice(10);
		cartDeletePojo.setAccountId("1");
		cartDeletePojo.setSizeId("1");
		cartDeletePojo.setColorId("1");
		List<CartDeletePojo> prepareToDelete=new ArrayList<>();
		prepareToDelete.add(cartDeletePojo);
		CartsDeleteRequest lstCartDelete=new CartsDeleteRequest(prepareToDelete,"");
		when(cartsServiceImp.deleteMutipleProductInCart(lstCartDelete)).thenReturn(successResponse);
		mockMvc.perform(delete("/api/v1/cart").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(lstCartDelete)))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void deleteMutipleProductInCart_ShoudReturnNotFound_WhenDataInValid() throws Exception {
		SuccessResponse successResponse = new SuccessResponse("200", "delete product success", null);
		CartDeletePojo cartDeletePojo=new CartDeletePojo();
		cartDeletePojo.setId("10");
		cartDeletePojo.setProductId("1");
		cartDeletePojo.setQuantity(10);
		cartDeletePojo.setPrice(10);
		cartDeletePojo.setAccountId("1");
		cartDeletePojo.setSizeId("1");
		cartDeletePojo.setColorId("10");
		List<CartDeletePojo> prepareToDelete=new ArrayList<>();
		prepareToDelete.add(cartDeletePojo);
		CartsDeleteRequest lstCartDelete=new CartsDeleteRequest(prepareToDelete,"");
		when(cartsServiceImp.deleteMutipleProductInCart(any())).thenThrow(NotFoundException.class);
		mockMvc.perform(delete("/api/v1/cart").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(lstCartDelete)))
				.andExpect(status().isNotFound());
	}
	
}
 