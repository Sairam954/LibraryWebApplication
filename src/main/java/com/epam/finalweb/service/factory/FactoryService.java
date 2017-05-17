package com.epam.finalweb.service.factory;

import com.epam.finalweb.service.BookService;
import com.epam.finalweb.service.UserService;
import com.epam.finalweb.service.impl.BookServiceImpl;
import com.epam.finalweb.service.impl.UserServiceImpl;

public enum FactoryService {

	INSTANCE;
	
	private UserService userService=new UserServiceImpl();
	private BookService bookService=new BookServiceImpl();
	public BookService getBookService() {
		return bookService;
	}
	public UserService getUserService() {
		return userService;
	}
	
	
}
