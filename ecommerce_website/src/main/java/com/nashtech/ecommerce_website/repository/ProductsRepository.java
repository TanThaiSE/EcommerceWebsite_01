package com.nashtech.ecommerce_website.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.request.ProductCreateRequest;
import com.nashtech.ecommerce_website.dto.request.ProductUpdateRequest;
import com.nashtech.ecommerce_website.dto.request.RatingAddRequest;
import com.nashtech.ecommerce_website.dto.response.ProductsInCategoryDto;
import com.nashtech.ecommerce_website.entity.Products;
import com.nashtech.ecommerce_website.pojo.AttributeProductPojo;
import com.nashtech.ecommerce_website.pojo.DetailProductPojo;
import com.nashtech.ecommerce_website.pojo.ImageProductPojo;

@Repository
public interface ProductsRepository extends JpaRepository<Products,String> {
	
	@Query(value = "call GetDetailProduct(:productId)",nativeQuery = true)
	public Optional<DetailProductPojo> getDetailProduct(@Param("productId") String productId);
	
	@Query(value = "call GetColorProduct(:productId)",nativeQuery = true)
	public List<AttributeProductPojo> getColorProduct(@Param("productId") String productId);
	
	@Query(value = "call GetSizeProduct(:productId)",nativeQuery = true)
	public List<AttributeProductPojo> getSizeProduct(@Param("productId") String productId);
	
	@Query(value = "call GetImageProduct(:productId)",nativeQuery = true)
	public List<ImageProductPojo> getImageProduct(@Param("productId") String productId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="call UpdateNumberBuyProduct(:id,:quantity)",nativeQuery = true)
	public int updateNumberBuyProduct(@Param("id") String id,@Param("quantity") int quantity);	

	@Modifying
	@Transactional
	@Query(value="call AddNewProduct(:productId,:#{#c.name},:#{#c.detail},:#{#c.description},:#{#c.price},:#{#c.createdDate},:#{#c.updateDate},:#{#c.rate},:#{#c.numberBuy},:#{#c.categoryId})",nativeQuery = true)
	public int createNewProduct(@Param("productId")String productId,@Param("c") ProductCreateRequest productCreate);
	
	@Query(value = " SELECT p.id,  p.name as nameProduct,  p.price,  p.rate,  p.number_buy ,p.status_product as statusProduct,  imgp.name_image as nameImg FROM product as p INNER JOIN image imgp ON p.id=imgp.product_id WHERE imgp.index_image=0 and p.name like :keySearch",
	countQuery = "SELECT COUNT(p.id),  p.name as nameProduct,  p.price,  p.rate,  p.number_buy ,p.status_product as statusProduct,  imgp.name_image as nameImg FROM product as p INNER JOIN image imgp ON p.id=imgp.product_id WHERE imgp.index_image=0 and p.name like :keySearch",
	nativeQuery = true)
	public Page<ProductsInCategoryDto> getAllProduct(@Param("keySearch")String keySearch,Pageable pageable);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="call UpdateStatusProduct(:id,:statusProduct)",nativeQuery = true)
	public int updateStatusProduct(@Param("id") String id,@Param("statusProduct") int statusProduct);	
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="call UpdateProduct(:#{#c.id},:#{#c.name},:#{#c.detail},:#{#c.description},:#{#c.price},:#{#c.updateDate},:#{#c.categoryId})",nativeQuery = true)
	public int updateProduct(@Param("c") ProductUpdateRequest productUpdateRequest);	
}
