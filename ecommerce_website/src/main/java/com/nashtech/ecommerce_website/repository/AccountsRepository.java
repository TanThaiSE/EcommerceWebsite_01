package com.nashtech.ecommerce_website.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.request.RegisterRequest;
import com.nashtech.ecommerce_website.entity.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,String>  {
	@Query(value = "call GetAccountLogin(:phone)",nativeQuery = true)
	public Map<String,Object> findByPhone(@Param("phone") String phone);
	
	@Modifying
	@Transactional
	@Query(value = "call registerAccount(:#{#c.id},:#{#c.email},:#{#c.password},:#{#c.roleId},:#{#c.isBlocked},:#{#c.phone})",nativeQuery = true)
	public void registerAccount(@Param("c") RegisterRequest registerRequest);
	
	public List<Accounts> findByEmailOrPhone(String email,String phone);
}
