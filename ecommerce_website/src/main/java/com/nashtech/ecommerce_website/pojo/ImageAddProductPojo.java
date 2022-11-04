package com.nashtech.ecommerce_website.pojo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ImageAddProductPojo {
	private String id;
	@NotBlank(message = "name image is required")
	private String nameImage;
	@Min(value = 0,message = "index image should be a positive number")
	private int indexImage;
	@NotBlank(message = "product id is required")
	private String productId;
	
	public ImageAddProductPojo(String id,  String nameImage,int indexImage, String productId) {
		this.id = id;
		this.nameImage = nameImage;
		this.indexImage = indexImage;
		this.productId = productId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNameImage() {
		return nameImage;
	}
	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
	}
	public int getIndexImage() {
		return indexImage;
	}
	public void setIndexImage(int indexImage) {
		this.indexImage = indexImage;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
