package com.epam.finalweb.dao.factory;

import com.epam.finalweb.dao.book.BookDao;
import com.epam.finalweb.dao.book.impl.BookDaoImpl;
import com.epam.finalweb.dao.user.UserDao;
import com.epam.finalweb.dao.user.impl.UserDaoImpl;

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
