package com.epam.finalweb.service;

import java.util.List;

import com.epam.finalweb.domain.User;
import com.epam.finalweb.domain.UserRegistrationForm;

import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.exception.ValidationException;

public interface UserService {

	User getUser(String emailId, String password) throws ServiceException, ValidationException;
	User getUser(int userId) throws ServiceException;
	List<User> getAllUser() throws ServiceException;
	boolean validateUserEmail(String emailId) throws ServiceException;

	void createUser(UserRegistrationForm form) throws ServiceException,ValidationException;
	
	void updateUser(User user,String oldPassword,String newPassword) throws ServiceException,ValidationException;
	boolean verifyPassword(int userId,String password) throws ServiceException;
}
