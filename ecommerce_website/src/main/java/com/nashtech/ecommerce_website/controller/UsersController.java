//package com.nashtech.ecommerce_website.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.nashtech.ecommerce_website.entity.Users;
//import com.nashtech.ecommerce_website.services.UsersServiceImp;
//
//
//@RestController
//@RequestMapping("/account")
//public class UsersController {
//
//	@Autowired
//	UsersServiceImp usersServiceImp;
//	
//	@GetMapping("/getall")
//	public ResponseEntity<?> index(){
//		return new ResponseEntity<List<Users>>(usersServiceImp.getAllUser(),HttpStatus.OK);
//	}
//	
//	@GetMapping("/{userName}")
//	public ResponseEntity<?> getUserByUserName(@PathVariable("userName") String userNAME){
//		return new ResponseEntity<List<Users>>(usersServiceImp.getUserByUserName(userNAME),HttpStatus.OK);
//	}
//}
