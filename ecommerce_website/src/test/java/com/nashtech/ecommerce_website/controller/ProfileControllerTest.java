package com.nashtech.ecommerce_website.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.nashtech.ecommerce_website.dto.request.ProfileRequest;
import com.nashtech.ecommerce_website.dto.request.ProfileUpdateInfoRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Accounts;
import com.nashtech.ecommerce_website.entity.Profiles;
import com.nashtech.ecommerce_website.entity.Roles;
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
	
	@Test
	@WithMockUser(value ="user",roles = { "USER","ADMIN" })
	public void showDetailProfile_ShouldReturnSuccessResponse_WhenGetSuccess() throws JsonProcessingException, Exception {
		String idAccount="1";
		Profiles profiles=new Profiles();
		profiles.setId("1");
		profiles.setName("abc");
		profiles.setSex(0);
		profiles.setBirth(new Date());
		profiles.setAddress("abc");
		Roles roles=new Roles();
		roles.setId("1");
		roles.setName("abcd");
		Accounts acc=new Accounts();
		acc.setId(idAccount);
		acc.setEmail("Abc@gmail.com");
		acc.setPassword("123456789As!");
		acc.setIsBlocked(0);
		acc.setPhone("123");
		acc.setCreatedDate(new Date());
		profiles.setAccountsProfiles(acc);
		SuccessResponse result = new SuccessResponse("202","get detail users success",profiles);
		when(profileServiceImp.showDetailProfile(idAccount)).thenReturn(result);
		mockMvc.perform(get("/api/v1/profile/{idAccount}/detail-infor",idAccount)).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER","ADMIN" })
	public void updateInforProfile_ShouldReturnSuccessResponse_WhenUpdateSuccess() throws JsonProcessingException, Exception {
		ProfileUpdateInfoRequest profileUpdateInfoRequest=new ProfileUpdateInfoRequest("1","1", "abc", 0, new Date(),"abc");
		SuccessResponse result = new SuccessResponse("202","update user success",profileUpdateInfoRequest);
		when(profileServiceImp.updateInfoUser(profileUpdateInfoRequest)).thenReturn(result);

		mockMvc.perform(put("/api/v1/profile").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(profileUpdateInfoRequest))).andExpect(status().isOk());
	}
}

