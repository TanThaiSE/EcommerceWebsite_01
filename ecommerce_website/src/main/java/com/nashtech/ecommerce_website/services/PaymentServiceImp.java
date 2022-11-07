package com.nashtech.ecommerce_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Payments;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.repository.PaymentRepository;

@Service
public class PaymentServiceImp implements PaymentService{
	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	public SuccessResponse getAllPayment() {
		List<Payments> lst=paymentRepository.findAll();
		if(lst.size()>0) {
			return new SuccessResponse("200","get all payment success", lst);
		}
		throw new NotFoundException("Not found payment");
	}

}
