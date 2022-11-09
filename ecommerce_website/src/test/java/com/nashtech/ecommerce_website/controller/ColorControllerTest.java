package com.nashtech.ecommerce_website.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.nashtech.ecommerce_website.dto.request.AttributeAddRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Colors;
import com.nashtech.ecommerce_website.pojo.AttributeAddProductPojo;
import com.nashtech.ecommerce_website.services.ColorProductServiceImp;
import com.nashtech.ecommerce_website.services.ColorServiceImp;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ColorControllerTest {
	@MockBean
	ColorServiceImp colorServiceImp;
	@MockBean
	ColorProductServiceImp colorProductServiceImp;
	@Autowired
	private MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	@WithMockUser(value ="admin",roles = { "ADMIN" })
	public void getAllColor_ShouldReturnSuccessResponse_WhenGetSuccess() throws JsonProcessingException, Exception {
		List<Colors> lstColors=new ArrayList<>();
		Colors c=new Colors();
		c.setId("1");
		c.setName("abc");
		lstColors.add(c);
		SuccessResponse res= new SuccessResponse("200", "get all color success", lstColors);
		
		when(colorServiceImp.getAllColor()).thenReturn(res);
		
		mockMvc.perform(get("/api/v1/color")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="admin",roles = { "ADMIN" })
	public void deleteColorProduct_ShouldReturnSuccessResponse_WhenDeleteSuccess() throws JsonProcessingException, Exception {
		String idProduct="1";
		SuccessResponse res= new SuccessResponse("202","delete color product success", idProduct);
		when(colorProductServiceImp.deleteColorProductByProductId(idProduct)).thenReturn(res);
		mockMvc.perform(delete("/api/v1/color/{idProduct}/product",idProduct)).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="admin",roles = { "ADMIN" })
	public void createColorForProduct_ShouldReturnSuccessResponse_WhenCreateSuccess() throws JsonProcessingException, Exception {
		String idProduct="1";
		AttributeAddProductPojo addProductPojo=new AttributeAddProductPojo("1", "1", "1");
		List<AttributeAddProductPojo> lst=new ArrayList<AttributeAddProductPojo>();
		lst.add(addProductPojo);
		AttributeAddRequest addRequest=new AttributeAddRequest(idProduct, lst);
		SuccessResponse res= new SuccessResponse("201","createColorProduct success", addRequest);
		
		when(colorProductServiceImp.deleteColorProductByProductId(idProduct)).thenReturn(res);
		mockMvc.perform(post("/api/v1/color").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(addRequest)))
				.andExpect(status().isOk());
	}
	
}
