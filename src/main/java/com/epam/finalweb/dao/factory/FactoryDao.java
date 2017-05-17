package com.epam.finalweb.dao.factory;

import com.epam.finalweb.dao.BookDao;
import com.epam.finalweb.dao.UserDao;
import com.epam.finalweb.dao.impl.BookDaoImpl;
import com.epam.finalweb.dao.impl.UserDaoImpl;

public enum FactoryDao {

	INSTANCE;

	private UserDao userDao = new UserDaoImpl();
	private BookDao bookDao = new BookDaoImpl();

	public BookDao getBookDao() {
		return bookDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

}
