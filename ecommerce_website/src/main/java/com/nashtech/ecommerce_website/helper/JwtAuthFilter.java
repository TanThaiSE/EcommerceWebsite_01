package com.nashtech.ecommerce_website.helper;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nashtech.ecommerce_website.pojo.LoginPojo;
import com.nashtech.ecommerce_website.services.UserDetailsServiceImp;

//import com.nashtech.ecommerce_website.security.AccountService;



public class JwtAuthFilter extends  OncePerRequestFilter{

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsServiceImp userDetailsServiceImp;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		
		String token=getJwtToken(request);                  
		if(jwtProvider.validationToken(token)) {
			LoginPojo jsonData=jwtProvider.decodeToken(token);
			try {
				User userDetail= (User) userDetailsServiceImp.loadUserByUsername(jsonData.getPhone());
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetail,null,userDetail.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			} catch (Exception e) {
				System.out.println("doFilterInternal"+ e);
			}
		}
		
//		  else { System.out.println("Auth: Đăng nhập thất bại "); }
		 
		
		filterChain.doFilter(request, response);
	}

	@Bean
	public String getJwtToken(HttpServletRequest request) {
		String authenToken=request.getHeader("Authorization");
		if(StringUtils.hasText(authenToken)&&authenToken.contains("Bearer")) {
			String token=authenToken.substring(7);
			return token;
		}
		return null;
	}
}
