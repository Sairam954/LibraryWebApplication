package com.epam.finalweb.service;

import com.epam.finalweb.domain.User;
import com.epam.finalweb.exception.ServiceEmailNotValidException;
import com.epam.finalweb.exception.ServiceEmptyFieldException;
import com.epam.finalweb.exception.ServiceException;



public interface UserService {

	
	User getUser(String emailId,String password) throws ServiceException,ServiceEmptyFieldException,ServiceEmailNotValidException;
	boolean validateUserEmail(String emailId) throws ServiceException;	
}
