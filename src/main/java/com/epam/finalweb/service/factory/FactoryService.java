package com.epam.finalweb.service.factory;

import com.epam.finalweb.service.UserService;
import com.epam.finalweb.service.impl.UserServiceImpl;

public enum FactoryService {

	INSTANCE;
	
	private UserService userService=new UserServiceImpl();

	public UserService getUserService() {
		return userService;
	}
	
	
}
