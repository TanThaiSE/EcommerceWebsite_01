package com.nashtech.ecommerce_website.services;

import com.nashtech.ecommerce_website.dto.request.EmailRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;

public interface EmailService {
	public SuccessResponse sendEmailToResetPassword(EmailRequest email);
}
