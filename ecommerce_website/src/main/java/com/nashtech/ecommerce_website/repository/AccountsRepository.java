package com.nashtech.ecommerce_website.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.dto.request.AccountCreateRequest;
import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.request.NewAccountRequest;
import com.nashtech.ecommerce_website.dto.request.RegisterRequest;
import com.nashtech.ecommerce_website.entity.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,String>  {
	@Query(value = "call GetAccountLogin(:phone)",nativeQuery = true)
	public Map<String,Object> findByPhone(@Param("phone") String phone);
	
	@Modifying
	@Transactional
	@Query(value = "call registerAccount(:#{#c.id},:#{#c.email},:#{#c.password},:#{#c.roleId},:#{#c.isBlocked},:#{#c.phone},:#{#c.createdDate})",nativeQuery = true)
	public int registerAccount(@Param("c") RegisterRequest registerRequest);
	
	public List<Accounts> findByEmailOrPhone(String email,String phone);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="call updateBlocked(:accountId,:isBlocked)",nativeQuery = true)
	public int updateBlocked(@Param("accountId") String accountId,@Param("isBlocked") int isBlocked);
	
	@Modifying
	@Transactional
	@Query(value = "call registerAccount(:#{#c.idAccount},:#{#c.email},:#{#c.password},:#{#c.roleId},:#{#c.isBlocked},:#{#c.phone},:#{#c.createdDate})",nativeQuery = true)
	public int addNewAccount(@Param("c") AccountCreateRequest registerRequest);

	
	public Optional<Accounts> findById(String id);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="call updatePassword(:accountId,:newPassword)",nativeQuery = true)
	public int updatePassword(@Param("accountId") String accountId,@Param("newPassword") String newPassword);
	
	public Optional<Accounts> findByEmail(String email);
}
