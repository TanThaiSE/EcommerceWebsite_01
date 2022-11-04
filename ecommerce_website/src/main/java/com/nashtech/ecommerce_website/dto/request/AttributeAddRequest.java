package com.nashtech.ecommerce_website.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.nashtech.ecommerce_website.pojo.AttributeAddProductPojo;

public class AttributeAddRequest {
	@NotBlank(message = "productId is required")
	private String productId;
	private List<AttributeAddProductPojo> listAttributes;
	
	
	public AttributeAddRequest(String productId,List<AttributeAddProductPojo> listAttributes) {
		this.productId = productId;
		this.listAttributes = listAttributes;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public List<AttributeAddProductPojo> getListAttributes() {
		return listAttributes;
	}
	public void setListAttributes(List<AttributeAddProductPojo> listAttributes) {
		this.listAttributes = listAttributes;
	}
	


	
}
