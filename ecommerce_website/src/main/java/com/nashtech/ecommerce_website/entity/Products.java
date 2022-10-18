package com.nashtech.ecommerce_website.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="product")
public class Products {
	@Id
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="detail")
	private String detail;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private int price;
	
	@Column(name="created_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name="updated_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date updatedDate ;
	
	@Column(name="rate")
	private float rate;
	
	@Column(name="number_buy")
	private int numberBuy;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Categorys categorysProducts;

	@OneToMany(mappedBy = "productsCart")
	private Set<Carts> carts;
	
	@OneToMany(mappedBy = "productsColorsProducts")
	private Set<ColorsProducts> colorsProducts;
	
	@OneToMany(mappedBy = "productsComments")
	private Set<Comments> comments;
	
	@OneToMany(mappedBy = "productsSizeProducts")
	private Set<SizeProducts> sizeProducts;
	
	@OneToMany(mappedBy = "productOderDetail")
	private Set<Orders> orderDetails;
	
	@OneToMany(mappedBy = "productsImages")
	private Set<Images> images;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}



	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public int getNumberBuy() {
		return numberBuy;
	}

	public void setNumberBuy(int numberBuy) {
		this.numberBuy = numberBuy;
	}

	public Categorys getCategorysProducts() {
		return categorysProducts;
	}

	public void setCategorysProducts(Categorys categorysProducts) {
		this.categorysProducts = categorysProducts;
	}
	
	
}
