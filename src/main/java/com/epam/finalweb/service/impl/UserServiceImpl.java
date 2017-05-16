package com.epam.finalweb.service.impl;

import com.epam.finalweb.dao.UserDao;
import com.epam.finalweb.dao.factory.FactoryDao;
import com.epam.finalweb.domain.User;
import com.epam.finalweb.exception.DaoException;
import com.epam.finalweb.exception.ServiceEmailNotValidException;
import com.epam.finalweb.exception.ServiceEmptyFieldException;
import com.epam.finalweb.exception.ServiceException;

import com.epam.finalweb.exception.ValidationException;
import com.epam.finalweb.service.UserService;
import com.epam.finalweb.util.Validation;

public class UserServiceImpl implements UserService{

	

	
	public boolean validateUserEmail(String emailId) throws ServiceException {
		UserDao userDao=FactoryDao.INSTANCE.getUserDao();
		try {
			return userDao.validateUserEmailId(emailId);
		} catch (DaoException e) {
			throw new ServiceException("Service Exception",e);
		}
	}

	public User getUser(String emailId,String password) throws ServiceException,ServiceEmptyFieldException,ServiceEmailNotValidException {

		try {
			Validation.validateEmailId(emailId);
			
			
			
		} catch (ValidationException e) {
			throw new ServiceEmailNotValidException("Not valid Email",e);
		}
		try {
			Validation.validatePassword(password);
			
			
			
		} catch (ValidationException e) {
			throw new ServiceEmptyFieldException("Password Cannot be Empty",e);
		}
		UserDao userDao=FactoryDao.INSTANCE.getUserDao();
		
		try {
			return userDao.getUser(emailId, password);
		} catch (DaoException e) {
			throw new ServiceException("Wrong Password Credentials",e);
		}
		
		
	}

}
