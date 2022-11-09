package com.nashtech.ecommerce_website.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Date;
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
import com.nashtech.ecommerce_website.dto.request.AccountCreateRequest;
import com.nashtech.ecommerce_website.dto.request.AccountUpdatePasswordRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.services.AccountsServiceImp;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountControllerTest {
	@MockBean
	AccountsServiceImp accountsServiceImp;
	@Autowired
	private MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	@WithMockUser(value ="admin",roles = { "USER","ADMIN" })
	public void updatePassword_ShouldReturnSuccessResponse_WhenUpdateSuccess() throws JsonProcessingException, Exception {
		AccountUpdatePasswordRequest accountUpdatePasswordRequest=new AccountUpdatePasswordRequest("1","123456789Bc@","123456789As#");
		SuccessResponse res=new SuccessResponse("202","update password success",accountUpdatePasswordRequest);
		when(accountsServiceImp.updatePassword(accountUpdatePasswordRequest)).thenReturn(res);
		mockMvc.perform(put("/api/v1/accounts").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(accountUpdatePasswordRequest)))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="admin",roles = { "USER","ADMIN" })
	public void updatePassword_ShouldReturnNotFound_WhenUpdateFailed() throws JsonProcessingException, Exception {
		AccountUpdatePasswordRequest accountUpdatePasswordRequest=new AccountUpdatePasswordRequest("1","1234567893Pc@","123456789As#");
		when(accountsServiceImp.updatePassword(any())).thenThrow(NotFoundException.class);
		mockMvc.perform(put("/api/v1/accounts").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(accountUpdatePasswordRequest)))
				.andExpect(status().isNotFound());
	}
	
	@Test
	@WithMockUser(value ="admin",roles = { "ADMIN" })
	public void createAccount_ShouldReturnSuccessResponse_WhenCreateSuccess() throws JsonProcessingException, Exception {
		AccountCreateRequest accountCreateRequest=new AccountCreateRequest("1","1", "abc@gmai.com","123456789As!","1234","Abc", 0,new Date(), "abcd", "1", new Date(), 0);
		SuccessResponse res=new SuccessResponse("202","update password success",accountCreateRequest);
		when(accountsServiceImp.createNewAccount(accountCreateRequest)).thenReturn(res);
		mockMvc.perform(post("/api/v1/accounts").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(accountCreateRequest)))
		.andExpect(status().isOk());	
	}
	
}
