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
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.test.web.servlet.MockMvc;

import com.nashtech.ecommerce_website.dto.response.ProductsInCategoryDto;
import com.nashtech.ecommerce_website.entity.Categorys;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.services.CategorysServiceImp;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CategorysControllerTest {
	@MockBean
	CategorysServiceImp categorysServiceImp;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getAllCategory_ShouldReturnListCategorys_WhenGetSuccess() throws Exception {
		Categorys categorys=new Categorys();
		categorys.setId("1");
		categorys.setName("abc");
		categorys.setImage("abc.jpg");
		List<Categorys> lst=new ArrayList<>();
		lst.add(categorys);
		when(categorysServiceImp.getAllCategory()).thenReturn(lst);
		mockMvc.perform(get("/api/v1/category")).andExpect(status().isOk());
	}
	
	@Test
	public void getAllCategory_ShouldReturnNotFound_WhenNotFoundData() throws Exception {
		when(categorysServiceImp.getAllCategory()).thenThrow(NotFoundException.class);
		mockMvc.perform(get("/api/v1/category")).andExpect(status().isNotFound());
	}
	
	@Test
	public void getProductsByCategoryId_ShouldReturnProductsInCategoryDto_WhenGetSuccess() throws Exception {
		ProjectionFactory factory=new SpelAwareProxyProjectionFactory();
		ProductsInCategoryDto pCategoryDto=factory.createProjection(ProductsInCategoryDto.class);
		pCategoryDto.setId("1");
		pCategoryDto.setPrice(20000);
		pCategoryDto.setRate(2);
		pCategoryDto.setNumber_buy(1);
		pCategoryDto.setNameProduct("abc");
		pCategoryDto.setNameImg("abc.jpg");
		List<ProductsInCategoryDto> lst=new ArrayList<>();
		lst.add(pCategoryDto);
		when(categorysServiceImp.getAllProductByCategory("1",0,1)).thenReturn(lst);
		mockMvc.perform(get("/api/v1/category/{idCategory}/product","1")).andExpect(status().isOk());
	}
	
	@Test
	public void getProductsByCategoryId_ShouldReturnNotFound_WhenNotFoundData() throws Exception {
		when(categorysServiceImp.getAllProductByCategory("10",0,1)).thenThrow(NotFoundException.class);
		mockMvc.perform(get("/api/v1/category/{idCategory}/product","10")).andExpect(status().isNotFound());
	}
}

 