//package com.nashtech.ecommerce_website.entity;
//
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.MapsId;
//import javax.persistence.OneToMany;
//
//@Entity(name="imageproduct")
//public class ImageProducts {
//	@EmbeddedId
//	private ImageProductsId id;
//	
//	@ManyToOne
//	@MapsId("product_id")
//	@JoinColumn(name="product_id")
//	private Products productsImageProducts;
//	
//	@ManyToOne
//	@MapsId("image_id")
//	@JoinColumn(name="image_id")
//	private Images imagesImageProducts;
//
//	public ImageProductsId getId() {
//		return id;
//	}
//
//	public void setId(ImageProductsId id) {
//		this.id = id;
//	}
//
//	public Products getProductsImageProducts() {
//		return productsImageProducts;
//	}
//
//	public void setProductsImageProducts(Products productsImageProducts) {
//		this.productsImageProducts = productsImageProducts;
//	}
//
//	public Images getImagesImageProducts() {
//		return imagesImageProducts;
//	}
//
//	public void setImagesImageProducts(Images imagesImageProducts) {
//		this.imagesImageProducts = imagesImageProducts;
//	}
//	
//	
////	@Id
////	private String id;
////	@Column(name = "name")
////	private String name;
////	@Column(name = "index")
////	private int index;
////	@OneToMany(mappedBy = "imageProductsProducts")
////	private Set<Products> products;
////	public String getId() {
////		return id;
////	}
////	public void setId(String id) {
////		this.id = id;
////	}
////	public String getName() {
////		return name;
////	}
////	public void setName(String name) {
////		this.name = name;
////	}
////	public int getIndex() {
////		return index;
////	}
////	public void setIndex(int index) {
////		this.index = index;
////	}
////	
//}
