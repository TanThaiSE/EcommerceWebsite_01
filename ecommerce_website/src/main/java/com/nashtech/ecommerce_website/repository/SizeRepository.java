package com.nashtech.ecommerce_website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.entity.Sizes;

@Repository
public interface SizeRepository extends JpaRepository<Sizes,String> {
	public List<Sizes> findAll();
}
