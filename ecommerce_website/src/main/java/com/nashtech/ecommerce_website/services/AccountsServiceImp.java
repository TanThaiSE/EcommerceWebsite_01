package com.nashtech.ecommerce_website.services;

import java.util.Map;

public interface AccountsServiceImp {
	public Map<String,Object> findByemail(String email);
}
