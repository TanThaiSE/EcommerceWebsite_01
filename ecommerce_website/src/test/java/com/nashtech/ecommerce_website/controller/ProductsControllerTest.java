package com.nashtech.ecommerce_website.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtech.ecommerce_website.dto.response.ProductDetailResponseDto;
import com.nashtech.ecommerce_website.dto.response.ProductUpdateResponse;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.pojo.AttributeProductPojo;
import com.nashtech.ecommerce_website.pojo.DetailProductPojo;
import com.nashtech.ecommerce_website.pojo.ImageProductPojo;
import com.nashtech.ecommerce_website.pojo.ListUpdateProductPojo;
import com.nashtech.ecommerce_website.services.ProductsServiceImp;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductsControllerTest {
	@MockBean
	ProductsServiceImp productsServiceImp;
	@Autowired
	private MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void getDetailProduct_ShouldReturnProductDetailResponseDto_WhenGetSuccess() throws Exception {
		ProjectionFactory factory=new SpelAwareProxyProjectionFactory();
		DetailProductPojo detailProduct;
		detailProduct=factory.createProjection(DetailProductPojo.class);
		detailProduct.setId("1");
		detailProduct.setDescriptionProduct("product good");
		detailProduct.setNumber_buy(10);
		detailProduct.setNameProduct("Shirt");
		detailProduct.setPrice(140000);
		detailProduct.setRate((float) 5.0);
		detailProduct.setDetail("Big");
		
		AttributeProductPojo colorProduct=factory.createProjection(AttributeProductPojo.class);
		colorProduct.setId("1");
		colorProduct.setName("White");
		List<AttributeProductPojo> getColorProduct=new ArrayList<>();
		getColorProduct.add(colorProduct);
		
		AttributeProductPojo sizeProduct=factory.createProjection(AttributeProductPojo.class);
		sizeProduct.setId("1");
		sizeProduct.setName("S");
		List<AttributeProductPojo> getSizeProduct=new ArrayList<>();
		getSizeProduct.add(sizeProduct);
		
		
		ImageProductPojo imageProduct=factory.createProjection(ImageProductPojo.class);
		imageProduct.setId("1");
		imageProduct.setIndex_image(0);
		imageProduct.setProduct_id("1");
		imageProduct.setName_image("abc.jpg");
		List<ImageProductPojo> getImageProduct=new ArrayList<>();
		getImageProduct.add(imageProduct);
		
		ProductDetailResponseDto p=new ProductDetailResponseDto(detailProduct, getColorProduct, getSizeProduct, getImageProduct);
		when(productsServiceImp.getDetailProduct("1")).thenReturn(p);
		mockMvc.perform(get("/api/v1/product/{idProduct}","4")).andExpect(status().isOk());
		
	}
	
	@Test
	public void getDetailProduct_ShouldReturnNotFound_WhenNotFoundProductById() throws Exception {
		when(productsServiceImp.getDetailProduct("4")).thenThrow(NotFoundException.class);
		mockMvc.perform(get("/api/v1/product/{idProduct}","4")).andExpect(status().isNotFound());
	}
	
	@Test
	public void updateNumberBuyListProduct_ShouldReturnListUpdateProductPojo_WhenDataValid() throws JsonProcessingException, Exception {
		ProductUpdateResponse productUpdate=new ProductUpdateResponse("1", "abc", "dbd", "Abc", 10, new Date(),new Date(), 2, 2);
		List<ProductUpdateResponse> lst=new ArrayList<>();
		lst.add(productUpdate);
		ListUpdateProductPojo lstUpdatePojo=new ListUpdateProductPojo(lst,"");
		when(productsServiceImp.updateNumberBuyProduct(lstUpdatePojo)).thenReturn(lstUpdatePojo);
		
		mockMvc.perform(put("/api/v1/product").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(lstUpdatePojo)))
				.andExpect(status().isOk());
		
	}
	
	@Test
	public void updateNumberBuyListProduct_ShouldReturnNotFound_WhenDataValid() throws JsonProcessingException, Exception {
		ProductUpdateResponse productUpdate=new ProductUpdateResponse("1", "abc", "dbd", "Abc", 10, new Date(),new Date(), 2, 2);
		List<ProductUpdateResponse> lst=new ArrayList<>();
		lst.add(productUpdate);
		ListUpdateProductPojo lstUpdatePojo=new ListUpdateProductPojo(lst,"");
		when(productsServiceImp.updateNumberBuyProduct(any())).thenThrow(NotFoundException.class);
		
		mockMvc.perform(put("/api/v1/product").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(lstUpdatePojo)))
				.andExpect(status().isNotFound());
		
	}
	
}
