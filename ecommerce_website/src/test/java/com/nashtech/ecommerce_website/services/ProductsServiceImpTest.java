package com.nashtech.ecommerce_website.services;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import com.nashtech.ecommerce_website.dto.response.ProductDetailResponseDto;
import com.nashtech.ecommerce_website.exceptions.GlobalExceptionsHandler;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.pojo.AttributeProductPojo;
import com.nashtech.ecommerce_website.pojo.DetailProductPojo;
import com.nashtech.ecommerce_website.pojo.ImageProductPojo;
import com.nashtech.ecommerce_website.repository.ProductsRepository;
import static org.hamcrest.MatcherAssert.assertThat;
public class ProductsServiceImpTest {
	ProductsServiceImp productsServiceImp;
	
	
	
	@Mock
	ProductsRepository productsRepository;
	
	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
		productsServiceImp=new ProductsServiceImp(productsRepository);
	}
	
	@Test
	public void getDetailProductById_ShouldReturnProduct_WhenDataValid() {
		ProjectionFactory factory=new SpelAwareProxyProjectionFactory();
		DetailProductPojo detailProduct=factory.createProjection(DetailProductPojo.class);
		detailProduct.setId("1");
		detailProduct.setDescriptionProduct("product good");
		detailProduct.setNumber_buy(10);
		detailProduct.setNameProduct("Shirt");
		detailProduct.setPrice(140000);
		detailProduct.setRate((float) 5.0);
		detailProduct.setDetail("Big");
		when(productsRepository.getDetailProduct("1")).thenReturn(Optional.of(detailProduct));
		
		List<AttributeProductPojo> getColorProduct=mock(List.class);
		AttributeProductPojo colorProduct=factory.createProjection(AttributeProductPojo.class);
		colorProduct.setId("1");
		colorProduct.setName("White");
		getColorProduct.add(colorProduct);
		Mockito.verify(getColorProduct).add(colorProduct);
		when(productsRepository.getColorProduct("1")).thenReturn(getColorProduct);
		
		
		List<AttributeProductPojo> getSizeProduct=mock(List.class);
		AttributeProductPojo sizeProduct=factory.createProjection(AttributeProductPojo.class);
		sizeProduct.setId("1");
		sizeProduct.setName("S");
		getSizeProduct.add(sizeProduct);
		Mockito.verify(getSizeProduct).add(sizeProduct);
		when(productsRepository.getSizeProduct("1")).thenReturn(getSizeProduct);
		
		List<ImageProductPojo> getImageProduct=mock(List.class);
		ImageProductPojo imageProduct=factory.createProjection(ImageProductPojo.class);
		imageProduct.setId("1");
		imageProduct.setIndex_image(0);
		imageProduct.setProduct_id("1");
		imageProduct.setName_image("abc.jpg");
		getImageProduct.add(imageProduct);
		Mockito.verify(getImageProduct).add(imageProduct);
		when(productsRepository.getImageProduct("1")).thenReturn(getImageProduct);

		ProductDetailResponseDto expectedValue=productsServiceImp.getDetailProduct("1");
		assertThat(detailProduct,is(expectedValue.getDetailProduct()));
		assertThat(getColorProduct,is(expectedValue.getColorProduct()));
		assertThat(getSizeProduct, is(expectedValue.getSizeProduct()));
		assertThat(getImageProduct, is(expectedValue.getImageProduct()));
	}
	
	@Test
	public void getDetailProductById_ShouldReturnException_WhenDataNotValid() {
		NotFoundException notFoundException=Assertions.assertThrows(NotFoundException.class,()->productsServiceImp.getDetailProduct("100"));
		Assertions.assertEquals("Not found detailproduct", notFoundException.getMessage());
	}
}
