package com.nashtech.ecommerce_website.pojo;

import java.util.Date;

public interface DetailProductPojo {
	 String getId();
	 String getDetail();
	 Integer getPrice(); 
	 Float getRate(); 
	 Integer getNumber_buy(); 
	 String getDescriptionProduct(); 
	 String getNameProduct(); 
	 Integer getStatusProduct();
	 String getCategoryId();
	 String getCategoryName();
	 Date getCreatedDate();
	 Date getUpdatedDate();
	 void setId(String id);
	 void setDetail(String detail);
	 void setPrice(int price);
	 void setRate(float rate);
	 void setNumber_buy(int number_buy);
	 void setDescriptionProduct(String descriptionProduct);
	 void setNameProduct(String nameProduct);
	 void setStatusProduct(int statusProduct);
	 void setCategoryId(String categoryId);
	 void setCategoryName(String categoryName);
	 void setCreatedDate(Date createdDate);
	 void setUpdatedDate(Date updatedDate);
}
