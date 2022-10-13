package com.nashtech.ecommerce_website.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.nashtech.ecommerce_website.helper.JwtProvider;
import com.nashtech.ecommerce_website.payload.LoginRequest;
import com.nashtech.ecommerce_website.services.AccountsServiceImp;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	AccountsServiceImp accountsServiceImp;

	private Gson gson = new Gson();
//	@PostMapping("/login")
//	public ResponseEntity<?>login(@RequestBody LoginRequest loginRequest){
//		//Hàm dùng để kích hoạt đăng nhập bằng tay
//		try {
//			Authentication authen= authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
//						);
//				SecurityContextHolder.getContext().setAuthentication(authen);
//				String jwtToken=jwtProvider.generateToken(loginRequest.getUserName());
//				Map<String,Object> acc=accountsServiceImp.findByemail(loginRequest.getUserName());
//				Map<String,Object> res=new HashMap<>();
//				res.put("token",jwtToken);
//				res.put("id",acc.get("id"));
//				res.put("username",loginRequest.getUserName());
//				res.put("type",1);
//				return new ResponseEntity<Map<String,Object>>(res,HttpStatus.OK);
//		} catch (Exception e) {
//			Map<String,Object> res=new HashMap<>();
//			res.put("type",-1);
//			return new ResponseEntity<Map<String,Object>>(res,HttpStatus.OK);
//			
//		}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		// Hàm dùng để kích hoạt đăng nhập bằng tay
		try {
			Authentication authen = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authen);
			Map<String, Object> acc = accountsServiceImp.findByemail(loginRequest.getUserName());
			Map<String, Object> genToken = new HashMap<>();
			genToken.put("id", acc.get("id"));
			genToken.put("username", loginRequest.getUserName());
			String jwtToken = jwtProvider.generateToken(gson.toJson(genToken));
			Map<String, Object> res = new HashMap<>();
			res.put("token", jwtToken);
			res.put("id", acc.get("id"));
			res.put("username", loginRequest.getUserName());
			res.put("type", 1);
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> res = new HashMap<>();
			res.put("type", -1);
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);

		}

	}

	@GetMapping("/test")
	public ResponseEntity<?> test() {
		return new ResponseEntity<String>("test", HttpStatus.OK);
	}
}
