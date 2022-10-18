package com.nashtech.ecommerce_website.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="comment")
public class Comments {
	@Id
	private String id;
	
	@Column(name = "content")
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Accounts accountsComments;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Products productsComments;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Accounts getAccountsComments() {
		return accountsComments;
	}

	public void setAccountsComments(Accounts accountsComments) {
		this.accountsComments = accountsComments;
	}

	public Products getProductsComments() {
		return productsComments;
	}

	public void setProductsComments(Products productsComments) {
		this.productsComments = productsComments;
	}

}
