package com.nashtech.ecommerce_website.services;

import java.util.List;

import com.nashtech.ecommerce_website.dto.request.OrderDetailRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;

public interface OrderDetailService {
	public SuccessResponse addToOrderDetail(OrderDetailRequest orderDetailRequest);
	
	public SuccessResponse getAllProductInOrderDetail();
}
