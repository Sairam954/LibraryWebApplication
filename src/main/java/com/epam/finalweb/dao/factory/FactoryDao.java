package com.epam.finalweb.dao.factory;

import com.epam.finalweb.dao.UserDao;
import com.epam.finalweb.dao.impl.UserDaoImpl;

public enum FactoryDao {

	INSTANCE;
	
	private UserDao userDao=new UserDaoImpl();

	public UserDao getUserDao() {
		return userDao;
	}
	
}
