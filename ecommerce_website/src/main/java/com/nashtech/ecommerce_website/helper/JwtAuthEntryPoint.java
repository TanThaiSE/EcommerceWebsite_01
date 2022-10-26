package com.nashtech.ecommerce_website.helper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtech.ecommerce_website.dto.response.AuthenResponse;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		AuthenResponse authenResponse=new AuthenResponse(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized", authException.getMessage(),request.getServletPath());
		ObjectMapper mapper=new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), authenResponse);
	}

}
