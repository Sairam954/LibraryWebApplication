package com.epam.finalweb.service;

import com.epam.finalweb.domain.User;
import com.epam.finalweb.domain.UserRegistrationForm;

import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.exception.ValidationException;

public interface UserService {

	User getUser(String emailId, String password) throws ServiceException, ValidationException;

	boolean validateUserEmail(String emailId) throws ServiceException;

	void createUser(UserRegistrationForm form) throws ServiceException,ValidationException;
}
