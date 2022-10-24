package com.nashtech.ecommerce_website.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.nashtech.ecommerce_website.dto.request.ProfileRequest;
import com.nashtech.ecommerce_website.entity.Profiles;

@Repository
public interface ProfileRepository extends JpaRepository<Profiles,String>{
	@Modifying
	@Transactional
	@Query(value = "call addToProfile(:#{#c.id},:#{#c.accountId},:#{#c.name},:#{#c.sex},:#{#c.birth},:#{#c.address})",nativeQuery = true)
	public void addToProfile(@Param("c") ProfileRequest profileRequest); 
}
