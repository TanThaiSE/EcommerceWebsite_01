package com.nashtech.ecommerce_website.dto.response;

public class SuccessResponse {
	private String code;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public SuccessResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
