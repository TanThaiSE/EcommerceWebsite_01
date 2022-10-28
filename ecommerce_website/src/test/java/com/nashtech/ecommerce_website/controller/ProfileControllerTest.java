package com.nashtech.ecommerce_website.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtech.ecommerce_website.dto.request.ProfileRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.ProfileServiceImp;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProfileControllerTest {
	
	@MockBean
	ProfileServiceImp profileServiceImp;
	@Autowired
	private MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void addToProfile_ShouldReturnSuccessResponse_WhenPostSuccess() throws JsonProcessingException, Exception {
		ProfileRequest profileRequest=new ProfileRequest("1", "1", "abc", 0, new Date(), "abcd");
		SuccessResponse result = new SuccessResponse("201", "add profile success", profileRequest);
		when(profileServiceImp.addToProfile(profileRequest)).thenReturn(result);
		mockMvc.perform(post("/api/v1/profile").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(profileRequest))).andExpect(status().isOk());
	}
}

