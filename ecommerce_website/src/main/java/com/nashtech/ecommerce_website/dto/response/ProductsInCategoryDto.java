package com.nashtech.ecommerce_website.dto.response;

public interface ProductsInCategoryDto {
	String getId();
	Integer getPrice();
	Float getRate();
	Integer getNumber_buy();
	String getNameProduct();
	String getNameImg();
	Integer getStatusProduct();
	
	void setId(String id);
	void setPrice(int price);
	void setRate(float rate);
	void setNumber_buy(int numberBuy);
	void setNameProduct(String nameProduct);
	void setNameImg(String nameImg);
	void setStatusProduct(Integer statusProduct);
}
