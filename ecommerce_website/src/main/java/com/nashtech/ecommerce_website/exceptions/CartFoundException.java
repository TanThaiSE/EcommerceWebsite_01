package com.nashtech.ecommerce_website.exceptions;

public class CartFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CartFoundException() {
		super();
	}

	public CartFoundException(String message) {
		super(message);
	}

	public CartFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
