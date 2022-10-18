package com.nashtech.ecommerce_website.helper;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	private long EXPIRED=8*60*60*1000;
	private String SECRET_KEY = "YWRtaW4xMjM0NTY3ODkwQGFkbWluMTIzNDU2Nzg5MEBhZG1pbjEyMzQ1Njc4OTBkZmRmZGZkZmRmZGZkZmRtaW4xMjM0NTY3ODkwZGZkZmRmZGZkZmRmZGY="; // 

	public String generateToken(String accountId) {
		Date now=new Date();
		Date expiredDate=new Date(now.getTime()+EXPIRED);
		return Jwts.builder().setSubject(accountId) .setIssuedAt(now) .setExpiration(expiredDate) 
				.signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
	}
	
	public String decodeToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
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
