//package com.nashtech.ecommerce_website.services;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.nashtech.ecommerce_website.entity.Users;
//import com.nashtech.ecommerce_website.repository.UsersRepository;
//
//@Service
//public class UsersServices implements UsersServiceImp{
//	@Autowired
//	UsersRepository usersRepository;
//
//	@Override
//	public List<Users> getAllUser() {
//		return usersRepository.findAll();
//	}
//
//	@Override
//	public List<Users> getUserByUserName(String userName) {
//		return usersRepository.findByuserName(userName);
//	}
//}
