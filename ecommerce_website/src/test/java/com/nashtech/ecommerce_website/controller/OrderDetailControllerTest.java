package com.nashtech.ecommerce_website.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.nashtech.ecommerce_website.dto.request.OrderDetailRequest;
import com.nashtech.ecommerce_website.dto.response.OrderDetailResponse;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.pojo.OrderDetailPojo;
import com.nashtech.ecommerce_website.services.OrderDetailServiceImp;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderDetailControllerTest {
	@MockBean
	OrderDetailServiceImp orderDetailServiceImp;
	
	@Autowired
	private MockMvc mockMvc;
	ObjectMapper mapper=new ObjectMapper();
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void addToOrderDetail_ShouldReturnSuccessResponse_WhenPostSuccess() throws JsonProcessingException, Exception {
		OrderDetailPojo orderDetailRequest=new OrderDetailPojo("1", "1", 10, 10, "1", "1", 10);
		List<OrderDetailPojo> lst=new ArrayList<>();
		lst.add(orderDetailRequest);
		OrderDetailRequest oDetailRequest=new OrderDetailRequest(lst, "abc", "1");
		SuccessResponse expected = new SuccessResponse("201", "add product to order detail success",orderDetailRequest);
		when(orderDetailServiceImp.addToOrderDetail(oDetailRequest)).thenReturn(expected);
		mockMvc.perform(post("/api/v1/orderdetail").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(oDetailRequest))).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void addToOrderDetail_ShouldReturnNotFound_WhenDataNotFound() throws JsonProcessingException, Exception {
		OrderDetailPojo orderDetailRequest=new OrderDetailPojo("10", "1", 10, 10, "1", "1", 10);
		List<OrderDetailPojo> lst=new ArrayList<>();
		lst.add(orderDetailRequest);
		OrderDetailRequest oDetailRequest=new OrderDetailRequest(lst, "abc", "10");
		SuccessResponse expected = new SuccessResponse("201", "add product to order detail success",orderDetailRequest);
		
		when(orderDetailServiceImp.addToOrderDetail(any())).thenThrow(NotFoundException.class);
		mockMvc.perform(post("/api/v1/orderdetail").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(oDetailRequest))).andExpect(status().isNotFound());
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void getAllProductInOrderDetail_ShouldReturnListOrderDetailResponse_WhenFoundData() throws Exception {
		List<OrderDetailResponse> lst=new ArrayList<>();
		OrderDetailResponse o=new OrderDetailResponse("1", 10, 10, 10, "1", "abc", "1", "sbc.png", "S", "white");
		lst.add(o);
		when(orderDetailServiceImp.getAllProductInOrderDetail()).thenReturn(lst);
		mockMvc.perform(get("/api/v1/orderdetail")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="user",roles = { "USER" })
	public void getAllProductInOrderDetail_ShouldReturnNotFound_WhenDataValid() throws Exception {
		when(orderDetailServiceImp.getAllProductInOrderDetail()).thenThrow(NotFoundException.class);
		mockMvc.perform(get("/api/v1/orderdetail")).andExpect(status().isNotFound());
	}
}
