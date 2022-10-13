package com.nashtech.ecommerce_website.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.entity.Categorys;

@Repository
public interface CategorysRepository extends JpaRepository<Categorys,String> {
	/*
	 * 	@Query(value="call GetPhimByQuocGia(:id)",nativeQuery = true)
	List<Map<String, ?>>getPhimByQuocGia(@Param("id") Integer id);
	 * */
	@Query(value = "call GetAllCategories",nativeQuery = true)
	public List<Map<String,?>> getAllCategories();
}
