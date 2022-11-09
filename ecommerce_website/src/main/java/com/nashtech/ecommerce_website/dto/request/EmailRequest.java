package com.nashtech.ecommerce_website.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class EmailRequest {
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",message = "Email is not valid")
	@NotBlank(message = "Email is required")
	private String email;



	public EmailRequest(String email,String k) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
