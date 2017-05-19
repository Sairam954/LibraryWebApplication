package com.epam.finalweb.service;

import java.util.List;

import com.epam.finalweb.exception.ServiceException;
import com.epam.finalweb.exception.ValidationException;
import com.epam.finalweb.service.factory.FactoryService;

public class Validation {

	public static void validateEmailId(String emailId) throws ValidationException {
		UserService userService = FactoryService.INSTANCE.getUserService();
		if (emailId == null || emailId.isEmpty()) {

			throw new ValidationException("EmailID cannot be Empty");
		}

		try {
			if (!userService.validateUserEmail(emailId)) {
				throw new ValidationException("Email Id Not Registered");
			}
		} catch (ServiceException e) {
			throw new ValidationException("Email Id Not Registered", e);
		}

	}

	public static void validatePassword(String password) throws ValidationException {

		if (password == null || password.isEmpty()) {

			throw new ValidationException("Password cannot be Empty");
		}

	}

	public static void validateString(List<String> fields) throws ValidationException {

		if (fields == null || fields.isEmpty()) {

			throw new ValidationException("Password cannot be Empty");
		}

	}
	public static void validateString(String fields[]) throws ValidationException {

		for(String field:fields){
		
			if (field == null || field.isEmpty()) {
	
				throw new ValidationException("Password cannot be Empty");
			}
		}
	}
	public static void ValidatePhoneNumber(Long phoneNumber)throws ValidationException {

		if (phoneNumber == null || phoneNumber==0) {

			throw new ValidationException("Password cannot be Empty");
		}

	} 

}
