package com.nashtech.ecommerce_website.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.nashtech.ecommerce_website.dto.request.RatingAddRequest;
import com.nashtech.ecommerce_website.dto.response.RateResponse;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.exceptions.ItemExistException;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.services.RatingServiceImp;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RatingControllerTest {
	@MockBean
	private RatingServiceImp ratingServiceImp;
	@Autowired
	private MockMvc mockMvc;
	ObjectMapper mapper=new ObjectMapper();
	
	RatingAddRequest ratingAddRequest;
	@BeforeEach
	public void beforeEach() {
		ratingAddRequest=new RatingAddRequest("1","1", 5,"abc",new Date());
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void addRatingProduct_ShouldReturnSuccessResponse_WhenPostSuccess() throws Exception {
		SuccessResponse expected = new SuccessResponse("201", "add rating success", ratingAddRequest);
		when(ratingServiceImp.addRatingProduct(ratingAddRequest)).thenReturn(expected);
		mockMvc.perform(post("/api/v1/rating").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.content(mapper.writeValueAsString(ratingAddRequest))).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void addRatingProduct_ShouldReturnConflig_WhenConflig() throws JsonProcessingException, Exception   {
		when(ratingServiceImp.addRatingProduct(any())).thenThrow(ItemExistException.class);
		mockMvc.perform(post("/api/v1/rating").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.content(mapper.writeValueAsString(ratingAddRequest))).andExpect(status().isConflict());
	}
	
	@Test
	public void getRatingAndCommentProduct_ShouldReturnListRateResponse_WhenGetSuccess() throws Exception {
		RateResponse r=new RateResponse("1", 4, "abc", new Date(),"abcd");
		List<RateResponse> lst=new ArrayList<>();
		lst.add(r);
		when(ratingServiceImp.getRatingAndCommentProduct("134")).thenReturn(lst);
		mockMvc.perform(get("/api/v1/rating/{idProduct}","134")).andExpect(status().isOk());
	}
	
	@Test
	public void getRatingAndCommentProduct_ShouldReturnNotFound_WhenNotFoundRatingById() throws Exception {
		when(ratingServiceImp.getRatingAndCommentProduct("4")).thenThrow(NotFoundException.class);
		mockMvc.perform(get("/api/v1/rating/{idProduct}","4")).andExpect(status().isNotFound());
	}
}