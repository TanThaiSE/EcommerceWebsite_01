package com.nashtech.ecommerce_website.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce_website.dto.request.LoginRequest;
import com.nashtech.ecommerce_website.dto.response.LoginResponseDto;
import com.nashtech.ecommerce_website.exceptions.NotFoundException;
import com.nashtech.ecommerce_website.helper.JwtProvider;

@Service
public class LoginServiceImp implements LoginService{
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	AccountsServiceImp accountsServiceImp;
	
	@Override
	public LoginResponseDto login(LoginRequest loginRequest) {
		try {
			Authentication authen = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authen);
			Map<String, Object> acc = accountsServiceImp.findByemail(loginRequest.getUserName());
			String jwtToken = jwtProvider.generateToken((String) acc.get("id"));
			LoginResponseDto loginResponseDto=new LoginResponseDto(loginRequest.getUserName(), jwtToken);
			return loginResponseDto;
		} catch (Exception e) {
			throw new NotFoundException("Cannot found account");
		}
	}

}
