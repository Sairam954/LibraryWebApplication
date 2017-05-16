package com.epam.finalweb.util;


import com.epam.finalweb.exception.ServiceException;
import com.epam.finalweb.exception.ValidationException;
import com.epam.finalweb.service.UserService;
import com.epam.finalweb.service.factory.FactoryService;

public class Validation {
	
	public static void validateEmailId(String emailId) throws ValidationException{
		UserService userService=FactoryService.INSTANCE.getUserService();
		if(emailId==null||emailId.isEmpty()){
			
			throw new ValidationException("EmailID cannot be Empty");
		}
		
			try {
				if(!userService.validateUserEmail(emailId))
				{
					throw new ValidationException("Email Id Not Registered");
				}
			} catch (ServiceException e) {
				throw new ValidationException("Email Id Not Registered",e);
			}
		
		
		
		
	}
	public static void validatePassword(String password) throws ValidationException{
		
		
		
		if(password==null||password.isEmpty()){
			
			throw new ValidationException("Password cannot be Empty");
		}
				
	}
	

}
