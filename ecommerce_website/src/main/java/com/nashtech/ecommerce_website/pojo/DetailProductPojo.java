package com.nashtech.ecommerce_website.pojo;

public interface DetailProductPojo {
	 String getId();
	 String getDetail();
	 Integer getPrice(); 
	 Float getRate(); 
	 Integer getNumber_buy(); 
	 String getDescriptionProduct(); 
	 String getNameProduct(); 
	 
	 void setId(String id);
	 void setDetail(String detail);
	 void setPrice(int price);
	 void setRate(float rate);
	 void setNumber_buy(int number_buy);
	 void setDescriptionProduct(String descriptionProduct);
	 void setNameProduct(String nameProduct);
	 
}
