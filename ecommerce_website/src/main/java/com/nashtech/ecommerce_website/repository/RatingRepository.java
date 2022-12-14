package com.nashtech.ecommerce_website.repository;



import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.dto.request.RatingAddRequest;
import com.nashtech.ecommerce_website.entity.Rating;


@Repository
public interface RatingRepository extends JpaRepository<Rating,String>  {
	@Modifying
	@Transactional
	@Query(value = "call AddRaringProduct(:#{#c.id},:#{#c.orderDetailId},:#{#c.pointRate},:#{#c.comment},:#{#c.ratingDate})",nativeQuery = true)
	public int addRatingToProduct(@Param("c") RatingAddRequest ratingAddRequest);
	
	@Query(value = "call findRatingWithOrderDetailId(:orderDetailId)",nativeQuery = true)
	public Map<String,Object> findRatingWithOrderDetailId(@Param("orderDetailId")String orderDetailId);
	
	@Query(value="call getRatingAndCommentProduct(:productId)",nativeQuery = true)
	public List<Map<String,Object>> getRatingAndCommentProduct(@Param("productId")String productId);
	
	@Query(value="call calAvgPointRate(:id)",nativeQuery = true)
	public float calAvgPointRate(@Param("id") String productId);
	

}
