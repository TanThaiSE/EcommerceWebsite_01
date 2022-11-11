package com.nashtech.ecommerce_website.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.AccountUpdatePasswordRequest;
import com.nashtech.ecommerce_website.dto.request.EmailRequest;
import com.nashtech.ecommerce_website.dto.response.SuccessResponse;
import com.nashtech.ecommerce_website.entity.Accounts;
import com.nashtech.ecommerce_website.exceptions.BadRequestException;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.repository.AccountsRepository;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import net.bytebuddy.utility.RandomString;


@Service
public class EmailServiceImp implements EmailService{
	
	private String apiKey="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";
	private String emailFrom="tanthai172k@gmail.com";

	
	
	@Autowired
	AccountsRepository accountsRepository;
	@Override
	public SuccessResponse sendEmailToResetPassword(EmailRequest emailRequest) {
		Optional<Accounts> account=accountsRepository.findByEmail(emailRequest.getEmail());
		if(account.isEmpty()) {
			throw new NotFoundException("Email not exist");
		}
		String newPassword=RandomString.make(10)+"As9!";
		BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
		String newPassHash=bc.encode(newPassword);
		AccountUpdatePasswordRequest ac=new AccountUpdatePasswordRequest(account.get().getId(),account.get().getPassword(),newPassHash );
		int isUpdate=accountsRepository.updatePassword(ac.getId(), ac.getNewPassword());
		if(isUpdate>0) {
			Email from = new Email(emailFrom);
			String subject = "Reset password";
			Email to = new Email(emailRequest.getEmail());
			Content content = new Content();
			content.setType("text/html");
			String contentSend="<html><body><h3>Hi"+emailRequest.getEmail()+"</h3><p>New password is:"+newPassword+"</p><b>Please not share with anyone</b></body></html>";
			
			content.setValue(contentSend);
			Mail mail = new Mail(from, subject, to, content);
			SendGrid sg = new SendGrid(apiKey);
		    Request request = new Request();
		    try {
	            request.setMethod(Method.POST);
	            request.setEndpoint("mail/send");
	            request.setBody(mail.build());
	            Response response = sg.api(request);
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
		    return new SuccessResponse("200", "send success", emailRequest);
		}
		throw new BadRequestException("Cannot send email");

	}
	
	
}
