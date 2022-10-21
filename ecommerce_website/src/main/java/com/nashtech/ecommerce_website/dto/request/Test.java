package com.nashtech.ecommerce_website.dto.request;

import java.util.List;

public class Test {
	private List<Integer> product_id_list;

	public List<Integer> getProduct_id_list() {
		return product_id_list;
	}

	public void setProduct_id_list(List<Integer> product_id_list) {
		this.product_id_list = product_id_list;
	}

	public Test(List<Integer> product_id_list,String y) {
		this.product_id_list = product_id_list;
	}
	
}
