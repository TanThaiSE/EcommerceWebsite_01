package com.nashtech.ecommerce_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.ecommerce_website.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders,String>{

}
