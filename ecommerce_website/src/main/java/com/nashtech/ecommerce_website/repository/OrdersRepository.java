package com.nashtech.ecommerce_website.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.entity.Orders;
import com.nashtech.ecommerce_website.pojo.OrdersPojo;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,String>{
	@Modifying
	@Transactional
	@Query(value = "call AddToOrders(:#{#c.id},:#{#c.orderDetailId},:#{#c.productId},:accountId)",nativeQuery = true)
	public void addToOrders(@Param("c") OrdersPojo ordersPojo,@Param("accountId")String accountId);
}
