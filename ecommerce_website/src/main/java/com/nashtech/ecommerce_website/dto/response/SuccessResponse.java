package com.nashtech.ecommerce_website.dto.response;

public class SuccessResponse {
	private String code;
	private String message;
	private Object dataResponse;
	

	public SuccessResponse(String code, String message, Object dataResponse) {
		this.code = code;
		this.message = message;
		this.dataResponse = dataResponse;
	}
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
	public Object getDataResponse() {
		return dataResponse;
	}
	public void setDataResponse(Object dataResponse) {
		this.dataResponse = dataResponse;
	}

}
