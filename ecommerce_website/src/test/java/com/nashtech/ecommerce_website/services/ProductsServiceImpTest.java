package com.nashtech.ecommerce_website.services;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import com.nashtech.ecommerce_website.dto.response.ProductDetailResponseDto;
import com.nashtech.ecommerce_website.dto.response.ProductUpdateResponse;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.pojo.AttributeProductPojo;
import com.nashtech.ecommerce_website.pojo.DetailProductPojo;
import com.nashtech.ecommerce_website.pojo.ImageProductPojo;
import com.nashtech.ecommerce_website.pojo.ListUpdateProductPojo;
import com.nashtech.ecommerce_website.repository.ProductsRepository;
import static org.hamcrest.MatcherAssert.assertThat;
public class ProductsServiceImpTest {
	ProductsServiceImp productsServiceImp;
	@Mock
	ProductsRepository productsRepository;
	@Spy
	List<ProductUpdateResponse> list=new ArrayList<>();
	DetailProductPojo detailProduct;
	
	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
		productsServiceImp=new ProductsServiceImp(productsRepository);
		ProjectionFactory factory=new SpelAwareProxyProjectionFactory();
		detailProduct=factory.createProjection(DetailProductPojo.class);
		detailProduct.setId("1");
		detailProduct.setDescriptionProduct("product good");
		detailProduct.setNumber_buy(10);
		detailProduct.setNameProduct("Shirt");
		detailProduct.setPrice(140000);
		detailProduct.setRate((float) 5.0);
		detailProduct.setDetail("Big");
	}
	
	@Test
	public void getDetailProductById_ShouldReturnProduct_WhenDataValid() {
		ProjectionFactory factory=new SpelAwareProxyProjectionFactory();
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
	
	@Test
	public void updateNumberBuyProduct_ShouldReturnListUpdateProductPojo_WhenDataValid() {
		ProductUpdateResponse pResponse=new ProductUpdateResponse("1","abc","abc","abc",10,new Date(), new Date(),(float) 1.0, 1);
		list.add(pResponse);
		Mockito.verify(list).add(pResponse);
		ListUpdateProductPojo listUpdateProductPojo=new ListUpdateProductPojo(list, "");
		when(productsRepository.getDetailProduct(list.get(0).getProductId())).thenReturn(Optional.of(detailProduct));
		when(productsRepository.updateNumberBuyProduct(list.get(0).getProductId(),list.get(0).getQuantity()+detailProduct.getNumber_buy())).thenReturn(1);
		ListUpdateProductPojo expected=productsServiceImp.updateNumberBuyProduct(listUpdateProductPojo);
		assertThat(listUpdateProductPojo, sameInstance(expected));
	}
	
	@Test
	public void updateNumberBuyProduct_ShouldReturnNotFoundException_WhenDataNotValid() {
		ProductUpdateResponse pResponse=new ProductUpdateResponse("10000","abc","abc","abc",10,new Date(), new Date(),(float) 1.0, 1);
		list.add(pResponse);
		Mockito.verify(list).add(pResponse);
		ListUpdateProductPojo listUpdateProductPojo=new ListUpdateProductPojo(list, "");
		NotFoundException notFoundException=Assertions.assertThrows(NotFoundException.class,()->productsServiceImp.updateNumberBuyProduct(listUpdateProductPojo));
		Assertions.assertEquals("Not found detailproduct", notFoundException.getMessage());
		
	}
}


/*

	@Override
	public ListUpdateProductPojo updateNumberBuyProduct(ListUpdateProductPojo productRequest) {
			for (ProductUpdateResponse p : productRequest.getPrepareToUpdate()) 
			{
				Optional<DetailProductPojo> detailProduct = productsRepository.getDetailProduct(p.getProductId());
				if (detailProduct.isEmpty())
				{
					throw new NotFoundException("Not found detailproduct");
				}
				DetailProductPojo detailProductPojo = detailProduct.get();
				try {
					int isUpdate = productsRepository.updateNumberBuyProduct(p.getProductId(),p.getQuantity() + detailProductPojo.getNumber_buy());
				} catch (Exception e) {
					// TODO: handle exception
					throw new SqlException("Cannot update quantity product in cart");
				}

			}
			return productRequest;
	}
*/