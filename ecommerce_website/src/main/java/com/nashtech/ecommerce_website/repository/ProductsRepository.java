package com.nashtech.ecommerce_website.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products,String> {
	@Query(value = "call GetAllProductByCategory(:categoryId)",nativeQuery = true)
	public List<Map<String,?>> getAllProductByCategory(@Param("categoryId") String categoryId);
	
	@Query(value = "call GetDetailProduct(:productId)",nativeQuery = true)
	public Map<String,Object> getDetailProduct(@Param("productId") String productId);
	
	@Query(value = "call GetColorProduct(:productId)",nativeQuery = true)
	public List<Map<String,Object>> getColorProduct(@Param("productId") String productId);
	
	@Query(value = "call GetSizeProduct(:productId)",nativeQuery = true)
	public List<Map<String,Object>> getSizeProduct(@Param("productId") String productId);
	
	@Query(value = "call GetImageProduct(:productId)",nativeQuery = true)
	public List<Map<String,Object>> getImageProduct(@Param("productId") String productId);
}
