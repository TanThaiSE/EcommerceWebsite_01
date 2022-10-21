package com.nashtech.ecommerce_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nashtech.ecommerce_website.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating,String>  {

}
