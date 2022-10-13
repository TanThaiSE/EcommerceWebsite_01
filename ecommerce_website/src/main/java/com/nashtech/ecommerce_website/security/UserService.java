//package com.nashtech.ecommerce_website.security;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.nashtech.ecommerce_website.entity.Users;
//import com.nashtech.ecommerce_website.services.UsersServiceImp;
//
//@Service
//public class UserService implements UserDetailsService {
//	@Autowired
//	UsersServiceImp usersServiceImp;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		List<SimpleGrantedAuthority> roles=new ArrayList<SimpleGrantedAuthority>();
//		SimpleGrantedAuthority roleAdmin=new SimpleGrantedAuthority("ROLE_ADMIN");
//		roles.add(roleAdmin);
//		List<Users> lst=usersServiceImp.getUserByUserName(username);
//		Users u=lst.get(0);
//		System.out.println("u: "+u.getUserName());
//		User user=new User(u.getUserName(), u.getPassword(), roles);
//		return user;
//	}
//
//}
