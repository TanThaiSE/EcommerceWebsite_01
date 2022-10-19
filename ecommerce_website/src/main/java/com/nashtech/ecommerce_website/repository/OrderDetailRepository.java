package com.nashtech.ecommerce_website.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.nashtech.ecommerce_website.entity.OrderDetail;
import com.nashtech.ecommerce_website.pojo.OrderDetailPojo;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String>{
	@Modifying
	@Transactional
	@Query(value = "call AddToOrderDetail(:#{#c.id},:#{#c.quantity},:deliveryId,:paymentId,:accountId,:#{#c.colorId},:#{#c.sizeId},:#{#c.price},:orderDate,:address)",nativeQuery = true)
	public void addToOrderDetail(@Param("c") OrderDetailPojo OrderDetailPojo,@Param("deliveryId") String deliveryId,@Param("paymentId") String paymentId,@Param("accountId") String accountId,@Param("orderDate") Date orderDate,@Param("address") String address);
}
