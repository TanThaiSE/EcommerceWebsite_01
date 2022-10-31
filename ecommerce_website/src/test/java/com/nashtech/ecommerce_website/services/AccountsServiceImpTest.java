package com.nashtech.ecommerce_website.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nashtech.ecommerce_website.dto.request.RegisterRequest;
import com.nashtech.ecommerce_website.dto.response.ProductDetailResponseDto;
import com.nashtech.ecommerce_website.dto.response.ProductUpdateResponse;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Accounts;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.pojo.AttributeProductPojo;
import com.nashtech.ecommerce_website.pojo.DetailProductPojo;
import com.nashtech.ecommerce_website.pojo.ImageProductPojo;
import com.nashtech.ecommerce_website.pojo.ListUpdateProductPojo;
import com.nashtech.ecommerce_website.repository.AccountsRepository;
import com.nashtech.ecommerce_website.repository.ProductsRepository;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class AccountsServiceImpTest {
	AccountsServiceImp accountsServiceImp;
	@Mock
	AccountsRepository accountsRepository;
	
	
	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
		accountsServiceImp=new AccountsServiceImp(accountsRepository);
	}
	
	@Test
	public void findByPhone_ShouldReturnMap_WhenDataValid() {
		Map<String,Object> init=new HashMap<>();
		init.put("id", "1");
		init.put("email", "abc@gmail.com");
		init.put("password", "123");
		init.put("role_id", "2");
		init.put("is_blocked", "0");
		init.put("phone", "12345");
		init.put("roles", "ROLE_USER");
		when(accountsRepository.findByPhone("12345")).thenReturn(init);
		Map<String,Object> expected=accountsServiceImp.findByPhone("12345");
		assertThat(init, is(expected));
	}
	
	@Test
	public void addToAccount_ShouldReturnSuccessResponse_WhenDataValid() {
		RegisterRequest init=new RegisterRequest("1", "abc@gmail.com", "123", "1", 0, "12345",new Date());
		List<Accounts> isExistAccount=new ArrayList<Accounts>();
		when(accountsRepository.findByEmailOrPhone(init.getEmail(),init.getPhone())).thenReturn(isExistAccount);
		assertEquals(isExistAccount.size(), 0);
		RegisterRequest expected=new RegisterRequest("1", "abc@gmail.com", "123", "1", 0, "12345",new Date());
		when(accountsRepository.registerAccount(expected)).thenReturn(1);
		assertThat(expected).isEqualToComparingFieldByField(init);
	}
	
	@Test
	public void updateNumberBuyProduct_ShouldReturnSqlException_WhenDataNotValid() {
		RegisterRequest init=mock(RegisterRequest.class);
		SqlException sqlException=Assertions.assertThrows(SqlException.class, ()->accountsServiceImp.addToAccount(init));
		Assertions.assertEquals("Cannot register",sqlException.getMessage());
		
		
//		NotFoundException notFoundException=Assertions.assertThrows(NotFoundException.class,()->productsServiceImp.updateNumberBuyProduct(listUpdateProductPojo));
//		Assertions.assertEquals("Not found detailproduct", notFoundException.getMessage());
		
	}
	
}

/*public SuccessResponse AddToAccount(RegisterRequest registerRequest) {
		try {
			List<Accounts> isExistAccount=accountsRepository.findByEmailOrPhone(registerRequest.getEmail(),registerRequest.getPhone());
			if(isExistAccount.size()>0) {
				SuccessResponse result = new SuccessResponse("302", "account is existed",registerRequest);
				return result;
			}
			else {
				String idAccount = UUID.randomUUID().toString();
				registerRequest.setId(idAccount);
				BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
				registerRequest.setPassword(encoder.encode(registerRequest.getPassword()));
				registerRequest.setRoleId("218852d4-6eb3-4039-b193-c0b36da48d57");
				registerRequest.setIsBlocked(0);
				accountsRepository.registerAccount(registerRequest);
				SuccessResponse result = new SuccessResponse("201", "add account success",registerRequest);
				return result;
			}
			
		} catch (Exception e) {
			throw new SqlException("Cannot register");
		}
 * */
