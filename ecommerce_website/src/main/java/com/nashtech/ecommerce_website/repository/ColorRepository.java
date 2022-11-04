package com.nashtech.ecommerce_website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.entity.Colors;

@Repository
public interface ColorRepository extends JpaRepository<Colors,String>{
	public List<Colors> findAll();
}
