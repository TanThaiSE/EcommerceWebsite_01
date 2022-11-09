package com.nashtech.ecommerce_website.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Colors;
import com.nashtech.ecommerce_website.entity.Payments;
import com.nashtech.ecommerce_website.services.PaymentServiceImp;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PaymentControllerTest {
	@MockBean
	PaymentServiceImp paymentServiceImp;
	@Autowired
	private MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();
	@Test
	public void getAllPayment_ShouldReturnSuccessResponse_WhenGetSuccess() throws JsonProcessingException, Exception {
		List<Payments> lst=new ArrayList<Payments>();
		Payments p=new Payments();
		p.setId("1");
		p.setName("abc");
		lst.add(p);
		SuccessResponse res=new SuccessResponse("200","get all payment success", lst);
		when(paymentServiceImp.getAllPayment()).thenReturn(res);
		mockMvc.perform(get("/api/v1/payment")).andExpect(status().isOk());
	}
}
