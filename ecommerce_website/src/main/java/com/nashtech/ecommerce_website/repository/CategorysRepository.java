package com.nashtech.ecommerce_website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.dto.response.ProductsInCategoryDto;
import com.nashtech.ecommerce_website.entity.Categorys;

@Repository
public interface CategorysRepository extends JpaRepository<Categorys,String> {
	public List<Categorys> findAll();
	@Query(value = "call GetAllProductByCategory(:categoryId)",nativeQuery = true)
	public List<ProductsInCategoryDto> getAllProductByCategory(@Param("categoryId") String categoryId);

}
