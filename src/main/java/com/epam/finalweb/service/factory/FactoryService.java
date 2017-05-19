package com.epam.finalweb.service.factory;

import com.epam.finalweb.service.BookService;
import com.epam.finalweb.service.PoolService;
import com.epam.finalweb.service.UserService;
import com.epam.finalweb.service.impl.BookServiceImpl;
import com.epam.finalweb.service.impl.PoolServiceImpl;
import com.epam.finalweb.service.impl.UserServiceImpl;

public enum FactoryService {

	INSTANCE;

	private UserService userService = new UserServiceImpl();
	private BookService bookService = new BookServiceImpl();
	private PoolService poolService = new PoolServiceImpl();

	public PoolService getPoolService() {
		return poolService;
	}

	public BookService getBookService() {
		return bookService;
	}

	public UserService getUserService() {
		return userService;
	}

}
