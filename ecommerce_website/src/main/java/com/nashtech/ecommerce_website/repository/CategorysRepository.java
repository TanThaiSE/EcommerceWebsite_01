package com.nashtech.ecommerce_website.repository;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.nashtech.ecommerce_website.dto.request.CategoryCreateRequest;
import com.nashtech.ecommerce_website.dto.response.ProductsInCategoryDto;
import com.nashtech.ecommerce_website.entity.Categorys;

@Repository
public interface CategorysRepository extends JpaRepository<Categorys,String> {
	public Page<Categorys> findAll(Pageable pageable);
//	@Query(value = "call GetAllProductByCategory(:categoryId)",nativeQuery = true)
//	public List<ProductsInCategoryDto> getAllProductByCategory(@Param("categoryId") String categoryId);

	@Modifying
	@Transactional
	@Query(value = "call createNewCategory(:#{#c.id},:#{#c.name},:#{#c.image})",nativeQuery = true)
	public void createNewCategory(@Param("c") CategoryCreateRequest categoryCreateRequest);
	

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "call updateCategory(:#{#c.id},:#{#c.name},:#{#c.image})",nativeQuery = true)
	public int updateCategory(@Param("c") CategoryCreateRequest categoryCreateRequest);
	
	@Query(value = " SELECT p.id,  p.name as nameProduct,  p.price,  p.rate,  p.number_buy ,p.status_product as statusProduct,  imgp.name_image as nameImg FROM product as p INNER JOIN image imgp ON p.id=imgp.product_id WHERE p.category_id=:categoryId and imgp.index_image=0",
	countQuery = "SELECT COUNT(p.id),  p.name as nameProduct,  p.price,  p.rate,  p.number_buy ,p.status_product as statusProduct,  imgp.name_image as nameImg FROM product as p INNER JOIN image imgp ON p.id=imgp.product_id WHERE p.category_id=:categoryId and imgp.index_image=0",
	nativeQuery = true)
	public Page<ProductsInCategoryDto> getAllProductByCategory(@Param("categoryId") String categoryId,Pageable pageable);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "call updateStatusCategory(:id)",nativeQuery = true)
	public int updateStatusCategory(@Param("id")String idCategory);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	public void deleteById(String productId);
	public List<Categorys> findAllByIdAndStatusCategorys(String category,int statusCategory);
}
