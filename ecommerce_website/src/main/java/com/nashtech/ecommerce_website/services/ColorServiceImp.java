package com.nashtech.ecommerce_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Colors;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.repository.ColorRepository;

@Service
public class ColorServiceImp implements ColorService {
	@Autowired
	ColorRepository colorRepository;
	
	@Override
	public SuccessResponse getAllColor() {
		List<Colors> lst=colorRepository.findAll();
		if(lst.size()>0) {
			return new SuccessResponse("200", "get all color success", lst);
		}
		throw new NotFoundException("Cannot found Color");
	}

}
