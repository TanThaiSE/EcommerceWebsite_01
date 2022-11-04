package com.nashtech.ecommerce_website.pojo;

public class AttributeAddProductPojo {
	private String id;
	private String productId;
	private String attributeId;
	
	public AttributeAddProductPojo(String id, String productId, String attributeId) {
		this.id = id;
		this.productId = productId;
		this.attributeId = attributeId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}
	
}
