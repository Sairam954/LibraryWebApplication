package com.epam.finalweb.dao;

import com.epam.finalweb.domain.User;
import com.epam.finalweb.exception.DaoException;

public interface UserDao {

	
	User getUser(String emailId,String password) throws DaoException;
	boolean validateUserEmailId(String emailId) throws DaoException;
	
}
