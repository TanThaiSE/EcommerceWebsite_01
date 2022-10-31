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

import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.entity.Products;
import com.nashtech.ecommerce_website.pojo.AttributeProductPojo;
import com.nashtech.ecommerce_website.pojo.DetailProductPojo;
import com.nashtech.ecommerce_website.pojo.ImageProductPojo;

@Repository
public interface ProductsRepository extends JpaRepository<Products,String> {
	
	@Query(value = "call GetDetailProduct(:productId)",nativeQuery = true)
	public Optional<DetailProductPojo> getDetailProduct(@Param("productId") String productId);
	
	@Query(value = "call GetColorProduct(:productId)",nativeQuery = true)
	public List<AttributeProductPojo> getColorProduct(@Param("productId") String productId);
	
	@Query(value = "call GetSizeProduct(:productId)",nativeQuery = true)
	public List<AttributeProductPojo> getSizeProduct(@Param("productId") String productId);
	
	@Query(value = "call GetImageProduct(:productId)",nativeQuery = true)
	public List<ImageProductPojo> getImageProduct(@Param("productId") String productId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="call UpdateNumberBuyProduct(:id,:quantity)",nativeQuery = true)
	public int updateNumberBuyProduct(@Param("id") String id,@Param("quantity") int quantity);	

}
