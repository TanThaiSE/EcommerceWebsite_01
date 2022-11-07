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
import com.nashtech.ecommerce_website.pojo.LoginPojo;

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
			Map<String, Object> acc = accountsServiceImp.findByPhone(loginRequest.getUserName());
			String jwtToken=jwtProvider.generateToken(new LoginPojo((String) acc.get("id"), (String) acc.get("phone")));
			LoginResponseDto loginResponseDto=new LoginResponseDto((String)acc.get("id"),(String)acc.get("email"),(String)acc.get("role_id"),(String)acc.get("roles") ,jwtToken);
			return loginResponseDto;
		} catch (Exception e) {
			throw new NotFoundException("Invalid username or password");
		}
	}

}
