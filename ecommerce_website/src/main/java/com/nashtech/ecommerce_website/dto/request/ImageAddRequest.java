package com.nashtech.ecommerce_website.dto.request;

import java.util.List;

import com.nashtech.ecommerce_website.pojo.ImageAddProductPojo;

public class ImageAddRequest {
	private List<ImageAddProductPojo> listImages;

	public ImageAddRequest(List<ImageAddProductPojo> listImages,String k) {
		this.listImages = listImages;
	}

	public List<ImageAddProductPojo> getListImages() {
		return listImages;
	}

	public void setListImages(List<ImageAddProductPojo> listImages) {
		this.listImages = listImages;
	}
}
