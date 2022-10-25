package com.nashtech.ecommerce_website.helper;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.nashtech.ecommerce_website.pojo.LoginPojo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	private long EXPIRED=8*60*60*1000;
	private String SECRET_KEY = "YWRtaW4xMjM0NTY3ODkwQGFkbWluMTIzNDU2Nzg5MEBhZG1pbjEyMzQ1Njc4OTBkZmRmZGZkZmRmZGZkZmRtaW4xMjM0NTY3ODkwZGZkZmRmZGZkZmRmZGY="; // 

	public String generateToken(LoginPojo loginPojo) {
		Date now=new Date();
		Date expiredDate=new Date(now.getTime()+EXPIRED);
		Claims claims=Jwts.claims().setSubject(loginPojo.getPhone());
		claims.put("id",loginPojo.getId());
		claims.put("phone",loginPojo.getPhone());
		return Jwts.builder().setClaims(claims) .setIssuedAt(now) .setExpiration(expiredDate) 
				.signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
	}

	public LoginPojo decodeToken(String token) {
		try {
			Claims body=Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
			LoginPojo loginPojo=new LoginPojo((String)body.get("id"),(String) body.get("phone"));
			return loginPojo;
		} catch (Exception e) {
			return null;
		}
	}


	public boolean validationToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
