package com.nashtech.ecommerce_website.security;

import java.util.List;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nashtech.ecommerce_website.helper.JwtAccessDenied;
import com.nashtech.ecommerce_website.helper.JwtAuthEntryPoint;
import com.nashtech.ecommerce_website.helper.JwtAuthFilter;
import com.nashtech.ecommerce_website.services.UserDetailsServiceImp;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsServiceImp userDetailsServiceImp;
	@Autowired
	JwtAuthEntryPoint jwtAuthEntryPoint;
	@Autowired
	JwtAccessDenied jwtAccessDenied;
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
		http
		.csrf().disable()
		.cors().and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).accessDeniedHandler(jwtAccessDenied).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeHttpRequests()
		
		.antMatchers(HttpMethod.GET,"/api/v1/category").permitAll()
		
		.antMatchers("/api/v1/login/**").permitAll()
		.antMatchers("/api/v1/product/**").permitAll()
		.antMatchers(HttpMethod.POST,"/api/v1/profile/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/v1/rating/**").permitAll()
		.antMatchers("/api/v1/signup/**").permitAll()
		.antMatchers("/api/v1/payment/**").permitAll()
		.antMatchers("/api/v1/email/**").permitAll()
		
		.antMatchers("/api/v1/cart/**").hasAuthority("ROLE_USER")
		.antMatchers("/api/v1/orderdetail/**").hasAuthority("ROLE_USER")
		.antMatchers(HttpMethod.POST,"/api/v1/rating/**").hasAuthority("ROLE_USER")


		
		.antMatchers(HttpMethod.GET,"/api/v1/profile/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/v1/profile/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/v1/accounts/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
		

		.antMatchers("/api/v1/users/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/api/v1/size/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/api/v1/color/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/api/v1/image/**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.GET,"/api/v1/category/manager/**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.POST,"/api/v1/category/**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/v1/category/**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.DELETE,"/api/v1/category/**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.POST,"/api/v1/accounts/**").hasAuthority("ROLE_ADMIN")
		.and().addFilterBefore(jwtAuthFilter(),UsernamePasswordAuthenticationFilter.class);
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
	
    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
