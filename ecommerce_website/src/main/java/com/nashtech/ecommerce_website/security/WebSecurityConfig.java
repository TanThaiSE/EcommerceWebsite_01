//package com.nashtech.ecommerce_website.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.nashtech.ecommerce_website.helper.JwtAuthFilter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//
//	@Autowired
//	UserService userService; 
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// TODO Auto-generated method stub	
//		http.csrf().disable().cors().disable()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and().authorizeHttpRequests()
//		.antMatchers("/api/v1/login").permitAll()
//		.anyRequest().authenticated()
//		.and().addFilterBefore(jwtAuthFilter(),UsernamePasswordAuthenticationFilter.class);
//	}
//	
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		// TODO Auto-generated method stub
//		return super.authenticationManagerBean();
//	}
//	
//	@Bean
//	public JwtAuthFilter jwtAuthFilter() {
//		return new JwtAuthFilter();
//	}

//}
package com.nashtech.ecommerce_website.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.nashtech.ecommerce_website.helper.JwtAuthFilter;
import com.nashtech.ecommerce_website.services.UserDetailsServiceImp;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsServiceImp userDetailsServiceImp;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsServiceImp).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub	
//		http
//		.csrf().disable()
//		.cors().disable()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//		.authorizeHttpRequests()
//		.antMatchers("/api/v1/login").permitAll()
//		.antMatchers("/api/v1/product").permitAll()
//		.anyRequest().authenticated().and().addFilterBefore(jwtAuthFilter(),UsernamePasswordAuthenticationFilter.class);

//		 http.csrf().disable().cors().disable()
//		 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//		 .authorizeRequests().antMatchers("/api/v1/product").permitAll().anyRequest().authenticated()
//		 .and().addFilterBefore(jwtAuthFilter(),UsernamePasswordAuthenticationFilter.class);

		http
		.csrf().disable()
		.cors().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeHttpRequests()
		.antMatchers("/api/v1/category/**").permitAll()
		.antMatchers("/api/v1/login/**").permitAll()
		.antMatchers("/api/v1/product/**").permitAll()
		.antMatchers("/api/v1/profile/**").permitAll()
		.antMatchers("/api/v1/profile/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/v1/rating/**").permitAll()
		.antMatchers("/api/v1/signup/**").permitAll()
		.anyRequest().authenticated().and().addFilterBefore(jwtAuthFilter(),UsernamePasswordAuthenticationFilter.class);
		/*
		http
		.csrf().disable()
		.cors().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeHttpRequests().antMatchers("/api/v1/login").permitAll()
		.anyRequest().authenticated().and().addFilterBefore(jwtAuthenFilter(),UsernamePasswordAuthenticationFilter.class);
	
		 */
		
//		http.csrf().disable().cors().disable()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and().authorizeHttpRequests()
//		.anyRequest().permitAll();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	@Bean
	public JwtAuthFilter jwtAuthFilter() {
		return new JwtAuthFilter();
	}
	
}
