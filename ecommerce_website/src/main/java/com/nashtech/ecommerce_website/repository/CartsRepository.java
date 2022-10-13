package com.nashtech.ecommerce_website.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;

import com.nashtech.ecommerce_website.entity.Carts;

@Repository
public interface CartsRepository extends JpaRepository<Carts, String>{
	
//	public List<Carts> findAll();
	
	@Modifying
	@Transactional
	@Query(value = "call AddToCart(:#{#c.id},:#{#c.productId},:#{#c.quantity},:#{#c.price},:accountId,:#{#c.sizeId},:#{#c.colorId},:#{#c.statusCart})",nativeQuery = true)
	public void addToCart(@Param("c") CartsRequestDto cartsRequestDto,@Param("accountId") String accountId);
	
	@Query(value = "call FindProductInCart(:#{#c.productId},:accountId,:#{#c.sizeId},:#{#c.colorId})",nativeQuery = true)
	public Map<String,Object> findProductInCart(@Param("c") CartsRequestDto cartsRequestDto,@Param("accountId") String accountId);
	
	@Modifying
	@Transactional
	@Query(value="call UpdateQuantityProductInCart(:id,:quantity)",nativeQuery = true)
	public void updateQuantityCart(@Param("id") String id,@Param("quantity") int quantity);
	
	@Query(value = "call GetAllProductInCart(:accountId)",nativeQuery = true)
	public List<Map<String,Object>> getAllProductInCart(@Param("accountId")String accountId);
	
	//////////////////////////
	
	
	

//	public List<Carts> findByAccountCart_Id(String id);
//	public List<Carts> 
	
//	List<Carts> findByAccountCart_Id(String id);
}
