package com.nashtech.ecommerce_website.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.dto.request.AccountCreateRequest;
import com.nashtech.ecommerce_website.dto.request.NewAccountRequest;
import com.nashtech.ecommerce_website.dto.request.ProfileRequest;
import com.nashtech.ecommerce_website.dto.request.ProfileUpdateInfoRequest;
import com.nashtech.ecommerce_website.entity.Profiles;

@Repository
public interface ProfileRepository extends JpaRepository<Profiles,String>{
	@Modifying
	@Transactional
	@Query(value = "call addToProfile(:#{#c.id},:#{#c.accountId},:#{#c.name},:#{#c.sex},:#{#c.birth},:#{#c.address})",nativeQuery = true)
	public void addToProfile(@Param("c") ProfileRequest profileRequest); 
	
	public Page<Profiles> findAllByOrderByAccountsProfiles_CreatedDateDesc(Pageable pageable);
	
	public Optional<Profiles> findAllByAccountsProfiles_Id(String accountId);
	

	@Modifying
	@Transactional
	@Query(value = "call addToProfile(:#{#c.idProfile},:#{#c.idAccount},:#{#c.name},:#{#c.sex},:#{#c.birth},:#{#c.address})",nativeQuery = true)
	public int addNewProfile(@Param("c") AccountCreateRequest registerRequest);

	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="call updateProfile(:#{#c.id},:#{#c.accountId},:#{#c.name},:#{#c.sex},:#{#c.birth},:#{#c.address})",nativeQuery = true)
	public int updateProfile(@Param("c") ProfileUpdateInfoRequest profileUpdateInfoRequest);
	
}
