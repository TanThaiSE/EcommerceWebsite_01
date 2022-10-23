package com.nashtech.ecommerce_website.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.CartsRequestDto;
import com.nashtech.ecommerce_website.dto.request.OrderDetailRequest;
import com.nashtech.ecommerce_website.dto.response.CartResponseDto;
import com.nashtech.ecommerce_website.dto.response.OrderDetailResponse;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.OrderDetail;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.exceptions.SqlException;
import com.nashtech.ecommerce_website.pojo.OrderDetailPojo;
import com.nashtech.ecommerce_website.pojo.OrdersPojo;
import com.nashtech.ecommerce_website.repository.CartsRepository;
import com.nashtech.ecommerce_website.repository.OrderDetailRepository;
import com.nashtech.ecommerce_website.repository.OrdersRepository;
import com.nashtech.ecommerce_website.repository.ProductsRepository;

//theem loi neu dang nhap sai, phan quyen unthorize:401
@Service
public class OrderDetailServiceImp implements OrderDetailService {

	@Autowired
	CartsRepository cartsRepository;

	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public SuccessResponse addToOrderDetail(OrderDetailRequest orderDetailRequest) {
		String accountId = "94288321-4c0a-404f-a0a9-c40ab7095602";
		List<OrderDetailPojo> lstOrederDetail = orderDetailRequest.getOrderDetails();
		if (lstOrederDetail.size() > 0) {
			for (OrderDetailPojo o : lstOrederDetail) {
				CartsRequestDto cartsRequestDto = modelMapper.map(o, CartsRequestDto.class);
				Map<String, Object> isValidValue = cartsRepository.findProductWithSizeAndColor(cartsRequestDto);
				if (isValidValue == null || isValidValue.isEmpty()) {
					throw new NotFoundException("Values are not correct");
				}
				try {
					orderDetailRepository.addToOrderDetail(o,orderDetailRequest.getPaymentId(), accountId, new Date(),orderDetailRequest.getAddress());
					String idOrder=UUID.randomUUID().toString();
					OrdersPojo ordersPojo=new OrdersPojo(idOrder, o.getId(),o.getProductId());
					ordersRepository.addToOrders(ordersPojo);					
					
				} catch (Exception e) {
					throw new SqlException("Cannot insert product to order detail: "+e.getMessage());
				}
			}
			SuccessResponse result = new SuccessResponse("201", "add product to order detail success",orderDetailRequest);
			return result;
		} else {
			throw new NotFoundException("Not found list order detail");
		}
	}

	@Override
	public List<OrderDetailResponse> getAllProductInOrderDetail() {
		String accountId = "94288321-4c0a-404f-a0a9-c40ab7095602";
		List<Map<String, Object>> product=orderDetailRepository.getAllOrderDeTail(accountId);
		if (product.size()==0 || product == null) {
			throw new NotFoundException("Not found product in order");
		}
		else {
			List<OrderDetailResponse> lst=new ArrayList<OrderDetailResponse>();
			for(Map<String, Object> o:product) {
				lst.add(modelMapper.map(o, OrderDetailResponse.class));
			}
			return lst;
		}
	}
}
