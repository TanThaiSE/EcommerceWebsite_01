package com.nashtech.ecommerce_website.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.request.ProductCreateRequest;
import com.nashtech.ecommerce_website.dto.request.RatingAddRequest;
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
	

}
