package com.nashtech.ecommerce_website.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="image")
public class Images {
	@Id
	private String id;
	
	@Column(name="name_image")
	private String nameImage;
	
	@Column(name="index_image")
	private int indexImage;
	
	@OneToMany(mappedBy = "imagesImageProducts")
	private Set<ImageProducts> imageProducts;

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
	
}
