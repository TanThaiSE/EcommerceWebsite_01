package com.nashtech.ecommerce_website.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.entity.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,String>  {
	@Query(value = "call GetAccountLogin(:userName)",nativeQuery = true)
	public Map<String,Object> findByemail(@Param("userName") String email);
}
