package com.nashtech.ecommerce_website.dto.response;

import java.util.List;

import com.nashtech.ecommerce_website.pojo.AttributeProductPojo;
import com.nashtech.ecommerce_website.pojo.DetailProductPojo;
import com.nashtech.ecommerce_website.pojo.ImageProductPojo;

public class ProductDetailResponseDto {
	DetailProductPojo detailProduct;
	List<AttributeProductPojo> colorProduct;
	List<AttributeProductPojo> sizeProduct;
	List<ImageProductPojo> imageProduct;
	
	public ProductDetailResponseDto(DetailProductPojo detailProduct, List<AttributeProductPojo> colorProduct,List<AttributeProductPojo> sizeProduct, List<ImageProductPojo> imageProduct) {
		this.detailProduct = detailProduct;
		this.colorProduct = colorProduct;
		this.sizeProduct = sizeProduct;
		this.imageProduct = imageProduct;
	}
	public DetailProductPojo getDetailProduct() {
		return detailProduct;
	}
	public void setDetailProduct(DetailProductPojo detailProduct) {
		this.detailProduct = detailProduct;
	}
	public List<AttributeProductPojo> getColorProduct() {
		return colorProduct;
	}
	public void setColorProduct(List<AttributeProductPojo> colorProduct) {
		this.colorProduct = colorProduct;
	}
	public List<AttributeProductPojo> getSizeProduct() {
		return sizeProduct;
	}
	public void setSizeProduct(List<AttributeProductPojo> sizeProduct) {
		this.sizeProduct = sizeProduct;
	}
	public List<ImageProductPojo> getImageProduct() {
		return imageProduct;
	}
	public void setImageProduct(List<ImageProductPojo> imageProduct) {
		this.imageProduct = imageProduct;
	}
}
