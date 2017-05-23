package com.epam.finalweb.service.impl;

import com.epam.finalweb.dao.exception.DaoException;
import com.epam.finalweb.dao.factory.FactoryDao;
import com.epam.finalweb.dao.user.UserDao;
import com.epam.finalweb.domain.User;
import com.epam.finalweb.domain.UserRegistrationForm;
import com.epam.finalweb.service.UserService;
import com.epam.finalweb.service.Validation;
import com.epam.finalweb.service.exception.ServiceEmailNotValidException;
import com.epam.finalweb.service.exception.ServiceEmptyFieldException;
import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.exception.ValidationException;

public class UserServiceImpl implements UserService {

	public boolean validateUserEmail(String emailId) throws ServiceException {
		UserDao userDao = FactoryDao.INSTANCE.getUserDao();
		try {
			return userDao.validateUserEmailId(emailId);
		} catch (DaoException e) {
			throw new ServiceException("Service Exception", e);
		}
	}

	public User getUser(String emailId, String password)
			throws ServiceException, ValidationException {

		Validation.validateEmailId(emailId);

		Validation.validatePassword(password);

		UserDao userDao = FactoryDao.INSTANCE.getUserDao();

		try {
			return userDao.getUser(emailId, password);
		} catch (DaoException e) {
			throw new ServiceException("Wrong Password Credentials", e);
		}

	}

	@Override
	public void createUser(UserRegistrationForm form) throws ServiceException, ValidationException {

		Validation.ValidateUserForm(form);
		UserDao userDao = FactoryDao.INSTANCE.getUserDao();

		try {
			userDao.createUser(form);
		} catch (DaoException e) {
			throw new ServiceException("Cannot create User", e);
		}

	}

}
