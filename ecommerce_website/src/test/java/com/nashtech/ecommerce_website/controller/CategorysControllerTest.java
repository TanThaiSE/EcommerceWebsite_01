package com.nashtech.ecommerce_website.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtech.ecommerce_website.dto.request.CategoryCreateRequest;
import com.nashtech.ecommerce_website.dto.response.ProductsInCategoryDto;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Categorys;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.services.CategorysServiceImp;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CategorysControllerTest {
	@MockBean
	CategorysServiceImp categorysServiceImp;
	ObjectMapper mapper = new ObjectMapper();
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
		SuccessResponse res=new SuccessResponse("200","get all categories success",lst);
		when(categorysServiceImp.getAllProductByCategory("1",0,3,"desc")).thenReturn(res);
		mockMvc.perform(get("/api/v1/category/{idCategory}/product","1")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="admin",roles = { "ADMIN" })
	public void createNewCategory_ShouldReturnSuccessResponse_WhenCreateSuccess() throws Exception {
		CategoryCreateRequest categoryCreateRequest=new CategoryCreateRequest("1","abcd","abc.png");
		SuccessResponse res=new SuccessResponse("200","create new category success",categoryCreateRequest);
		when(categorysServiceImp.createNewCategory(categoryCreateRequest)).thenReturn(res);
		mockMvc.perform(post("/api/v1/category").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(categoryCreateRequest)))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="admin",roles = { "ADMIN" })
	public void updateCategory_ShouldReturnSuccessResponse_WhenUpdateSuccess() throws Exception {
		CategoryCreateRequest categoryCreateRequest=new CategoryCreateRequest("1","abcd","abc.png");
		SuccessResponse res=new SuccessResponse("200","create new category success",categoryCreateRequest);
		when(categorysServiceImp.updateCategory(categoryCreateRequest)).thenReturn(res);
		mockMvc.perform(put("/api/v1/category").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(categoryCreateRequest)))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(value ="admin",roles = { "ADMIN" })
	public void deleteEmptyCategory_ShouldReturnSuccessResponse_WhenDeleteSuccess() throws Exception {
		String idCategory="12345";
		SuccessResponse res=new SuccessResponse("202","Delete category success",idCategory);
		when(categorysServiceImp.deleteEmptyCategory(idCategory)).thenReturn(res);
		mockMvc.perform(delete("/api/v1/category/{idCategory}",idCategory)).andExpect(status().isOk());
	}
}

 