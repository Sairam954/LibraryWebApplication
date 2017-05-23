package com.epam.finalweb.dao.user;

import com.epam.finalweb.dao.exception.DaoException;
import com.epam.finalweb.domain.User;
import com.epam.finalweb.domain.UserRegistrationForm;

public interface UserDao {

	
	User getUser(String emailId,String password) throws DaoException;
	boolean validateUserEmailId(String emailId) throws DaoException;
	void createUser(UserRegistrationForm user) throws DaoException;
}
