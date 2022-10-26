package com.nashtech.ecommerce_website.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.dto.request.CartUpdateQuantityRequest;
import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.entity.Carts;

@Repository
public interface CartsRepository extends JpaRepository<Carts, String>{
	@Modifying
	@Transactional
	@Query(value = "call AddToCart(:#{#c.id},:#{#c.productId},:#{#c.quantity},:#{#c.price},:accountId,:#{#c.sizeId},:#{#c.colorId})",nativeQuery = true)
	public void addToCart(@Param("c") CartsRequestDto cartsRequestDto,@Param("accountId") String accountId);
	
	@Query(value = "call FindProductInCart(:#{#c.productId},:accountId,:#{#c.sizeId},:#{#c.colorId})",nativeQuery = true)
	public Map<String,Object> findProductInCart(@Param("c") CartsRequestDto cartsRequestDto,@Param("accountId") String accountId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="call UpdateQuantityProductInCart(:id,:#{#c.quantity},:accountId)",nativeQuery = true)
	public int updateQuantityCart(@Param("id") String id,@Param("c") CartUpdateQuantityRequest cartsUpdateRequest,@Param("accountId") String accountId);
	
	@Query(value = "call GetAllProductInCartByAccountId(:accountId)",nativeQuery = true)
	public List<Map<String,Object>> getAllProductInCartByAccountId(@Param("accountId")String accountId);
	
	@Modifying
	@Transactional
	public void deleteById(String id);
	
	public Optional<Carts> findByIdAndAccountCart_Id(String id,String accountId);

	
	@Query(value = "call FindProductWithSizeAndColor(:#{#c.productId},:#{#c.sizeId},:#{#c.colorId})",nativeQuery = true)
	public Map<String,Object> findProductWithSizeAndColor(@Param("c") CartsRequestDto cartsRequestDto);
}
