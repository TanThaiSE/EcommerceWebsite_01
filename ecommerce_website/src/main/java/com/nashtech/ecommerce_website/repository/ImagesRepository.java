package com.nashtech.ecommerce_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.ecommerce_website.entity.Images;

public interface ImagesRepository extends JpaRepository<Images,String> {
//	public Images 
	public Images findByProductsImages_IdAndIndexImage(String idProduct,int indexImg);
}
