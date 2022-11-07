package com.nashtech.ecommerce_website.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.entity.Payments;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, String> {
	public List<Payments> findAll();
}
