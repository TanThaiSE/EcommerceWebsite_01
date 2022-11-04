package com.nashtech.ecommerce_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Sizes;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.repository.SizeRepository;

@Service
public class SizeServiceImp implements SizeService{
	@Autowired
	SizeRepository sizeRepository;
	
	@Override
	public SuccessResponse getAllSize() {
		List<Sizes> lst=sizeRepository.findAll();
		if(lst.size()>0) {
			return new SuccessResponse("200","get all size success", lst);
		}
		throw new NotFoundException("Cannot found size");
	}

}
