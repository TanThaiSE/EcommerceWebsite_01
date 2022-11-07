package com.nashtech.ecommerce_website.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.entity.Images;
import com.nashtech.ecommerce_website.pojo.ImageAddProductPojo;

@Repository
public interface ImagesRepository extends JpaRepository<Images,String> {
	public Images findByProductsImages_IdAndIndexImage(String idProduct,int indexImg);
	
	@Modifying
	@Transactional
	@Query(value="call AddNewImage(:#{#c.id},:#{#c.nameImage},:#{#c.indexImage},:#{#c.productId})",nativeQuery = true)
	public int createNewImage(@Param("c") ImageAddProductPojo imageAddProduct);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	public void deleteAllByProductsImages_Id(String productId);
	
}
