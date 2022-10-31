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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtech.ecommerce_website.dto.request.RegisterRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.services.AccountsServiceImp;

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes = WebSecurityConfigTest.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RegisterControllerTest {
	@MockBean
	private AccountsServiceImp accountsServiceImp;
	@Autowired
	private MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void signup_ShouldReturnSuccessResponse_WhenGetSuccess() throws Exception {
		RegisterRequest registerRequest = new RegisterRequest("1", "abc@gmail.com", "123456789bCC!", "1", 0, "0222255",new Date());
		SuccessResponse expected = new SuccessResponse("201", "add account success", registerRequest);
		when(accountsServiceImp.addToAccount(registerRequest)).thenReturn(expected);
		mockMvc.perform(post("/api/v1/signup").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(registerRequest)))
				.andExpect(status().isOk());
	}

	@Test
	public void signup_ShouldReturnExisted_WhenRegisterDuplicated() throws Exception {
		RegisterRequest registerRequest = new RegisterRequest("100", "abc@gmail.com", "123456789bCC!", "1", 0,
				"0222255",new Date());
		SuccessResponse expected = new SuccessResponse("302", "account is existed", registerRequest);
		when(accountsServiceImp.addToAccount(registerRequest)).thenReturn(expected);

		mockMvc.perform(post("/api/v1/signup").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(registerRequest)))
				.andExpect(status().isOk());
	}
}
