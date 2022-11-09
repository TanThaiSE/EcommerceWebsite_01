package com.nashtech.ecommerce_website.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import com.nashtech.ecommerce_website.dto.request.ImageAddRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.pojo.AttributeAddProductPojo;
import com.nashtech.ecommerce_website.pojo.ImageAddProductPojo;
import com.nashtech.ecommerce_website.services.ImageServiceImp;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ImageControllerTest {
	@MockBean
	ImageServiceImp imageServiceImp;
	@Autowired
	private MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	@WithMockUser(value ="admin",roles = { "ADMIN" })
	public void createImageForProduct_ShouldReturnSuccessResponse_WhenCreateSuccess() throws JsonProcessingException, Exception {
		ImageAddProductPojo img=new ImageAddProductPojo("1", "abc.png", 0, "1");
		List<ImageAddProductPojo> lst= new ArrayList<ImageAddProductPojo>();
		lst.add(img);
		ImageAddRequest imageAddRequest=new ImageAddRequest(lst,"");
		SuccessResponse res=new SuccessResponse("201","createImageProduct success", imageAddRequest);
		when(imageServiceImp.createImageProduct(imageAddRequest)).thenReturn(res);

		
		mockMvc.perform(post("/api/v1/image").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(imageAddRequest)))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="admin",roles = { "ADMIN" })
	public void deleteImageProduct_ShouldReturnSuccessResponse_WhenDeleteSuccess() throws JsonProcessingException, Exception {
		String productId="1";
		SuccessResponse res=new SuccessResponse("202","delete color product success", productId);
		when(imageServiceImp.deleteImageByProductId(productId)).thenReturn(res);
		mockMvc.perform(delete("/api/v1/image/{idProduct}/product",productId)).andExpect(status().isOk());
	}
	
}
