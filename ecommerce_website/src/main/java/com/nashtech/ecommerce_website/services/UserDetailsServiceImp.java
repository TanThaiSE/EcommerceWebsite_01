package com.nashtech.ecommerce_website.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
	AccountsServiceImp accountsServiceImp;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		try {
			Map<String, Object> acc = accountsServiceImp.findByemail(username);
			if (!acc.isEmpty() && acc != null) {
				SimpleGrantedAuthority role = new SimpleGrantedAuthority((String) acc.get("roles"));
				roles.add(role);
				User user = new User((String) acc.get("email"), (String) acc.get("password"), roles);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception

		}
		return null;
	}
}
