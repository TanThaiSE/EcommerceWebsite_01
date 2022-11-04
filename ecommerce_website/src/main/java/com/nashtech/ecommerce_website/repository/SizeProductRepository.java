package com.nashtech.ecommerce_website.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nashtech.ecommerce_website.entity.SizeProducts;
import com.nashtech.ecommerce_website.pojo.AttributeAddProductPojo;

public interface SizeProductRepository extends JpaRepository<SizeProducts,String> {

	@Modifying
	@Transactional
	@Query(value = "call createNewSizeProduct(:#{#c.attributeId},:#{#c.productId},:#{#c.id})",nativeQuery = true)
	public int createNewSizeProduct(@Param("c")AttributeAddProductPojo AttributeAddProduct);
}
