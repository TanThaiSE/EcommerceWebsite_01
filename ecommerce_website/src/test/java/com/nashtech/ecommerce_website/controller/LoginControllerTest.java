package com.nashtech.ecommerce_website.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.nashtech.ecommerce_website.dto.request.LoginRequest;
import com.nashtech.ecommerce_website.dto.response.LoginResponseDto;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.services.LoginServiceImp;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoginControllerTest {
	@MockBean
	LoginServiceImp loginServiceImp;
	@Autowired
	private MockMvc mockMvc;
	ObjectMapper mapper=new ObjectMapper();
	
	
	@Test
	public void login_ShouldReturnLoginResponseDto_WhenDataValid() throws JsonProcessingException, Exception {
		LoginRequest loginRequest=new LoginRequest();
		loginRequest.setUserName("user");
		loginRequest.setPassword("user");
		LoginResponseDto loginResponseDto=new LoginResponseDto("user","token");
		when(loginServiceImp.login(loginRequest)).thenReturn(loginResponseDto);
		mockMvc.perform(post("/api/v1/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(loginRequest))).andExpect(status().isOk());
	}
	
	@Test
	public void login_ShouldReturnNotFound_WhenDataNotValid() throws JsonProcessingException, Exception {
		LoginRequest loginRequest=new LoginRequest();
		loginRequest.setUserName("1");
		loginRequest.setPassword("1");
		when(loginServiceImp.login(any())).thenThrow(NotFoundException.class);
		mockMvc.perform(post("/api/v1/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(loginRequest))).andExpect(status().isNotFound());
	}
}
