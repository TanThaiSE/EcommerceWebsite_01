package com.nashtech.ecommerce_website.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.entity.ColorsProducts;
import com.nashtech.ecommerce_website.pojo.AttributeAddProductPojo;

@Repository
public interface ColorProductRepository extends JpaRepository<ColorsProducts,String> {
	
	@Modifying
	@Transactional
	@Query(value = "call createNewColorProduct(:#{#c.attributeId},:#{#c.productId},:#{#c.id})",nativeQuery = true)
	public int createNewColorProduct(@Param("c")AttributeAddProductPojo AttributeAddProduct);

}
