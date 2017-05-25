package com.epam.finalweb.service;

import java.util.List;

import com.epam.finalweb.domain.Book;
import com.epam.finalweb.domain.BookType;
import com.epam.finalweb.domain.UserRegistrationForm;
import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.exception.ValidationException;
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

	public static void validateString(String field) throws ValidationException {

		if (field == null || field.isEmpty()) {

			throw new ValidationException("Password cannot be Empty");
		}

	}
	public static void validateString(List<String> fields) throws ValidationException {

		for(String field:fields){
		
			if (field == null || field.isEmpty()) {
	
				throw new ValidationException("Fields cannot be Empty");
			}
		}
	}
	public static void ValidatePhoneNumber(Long phoneNumber)throws ValidationException {

		if (phoneNumber == null || phoneNumber==0) {

			throw new ValidationException("Password cannot be Empty");
		}

	} 
	public static void ValidateUserForm(UserRegistrationForm form) throws ValidationException
	{
		
		String email=form.getEmail();
		String userName=form.getUserName();
		String password=form.getPassword();
		Long phoneNumber=form.getPhoneNumber();
		if (email == null || email.isEmpty()) {

			throw new ValidationException("Email cannot be Empty");
		}
		if (userName == null || userName.isEmpty()) {

			throw new ValidationException("User Name cannot be Empty");
		}if (password == null || password.isEmpty()) {

			throw new ValidationException("Password cannot be Empty");
		}
		if (phoneNumber == null) {

			throw new ValidationException("Phone Number cannot be Empty");
		}
	}
	public static void validateNewBook(Book book) throws ValidationException{
		
		
		 String bookTitle=book.getBookTitle();
		 String bookAuthor=book.getBookAuthor();
		 BookType bookType=book.getBookType();
	 String bookLanguage=book.getBookLanguage();
		 String description=book.getDescription();
		 if (bookTitle == null || bookTitle.isEmpty()) {

				throw new ValidationException("bookTitle cannot be Empty");
			}
		 if (bookAuthor == null || bookAuthor.isEmpty()) {

				throw new ValidationException("bookAuthor cannot be Empty");
			}
		 if (bookType == null ) {

				throw new ValidationException("bookType cannot be Empty");
			}
		 if (bookLanguage == null || bookLanguage.isEmpty()) {

				throw new ValidationException("bookLanguage  cannot be Empty");
			}
		 if (description == null || description.isEmpty()) {

				throw new ValidationException("description cannot be Empty");
			}
		
	}

}
