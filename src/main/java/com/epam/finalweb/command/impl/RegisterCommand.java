package com.epam.finalweb.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.finalweb.command.Command;
import com.epam.finalweb.domain.UserRegistrationForm;
import com.epam.finalweb.exception.ServiceException;
import com.epam.finalweb.service.UserService;
import com.epam.finalweb.service.factory.FactoryService;

public class RegisterCommand implements Command{
	private static final String LOGIN_USERSUCESS_PAGE="LoginSucessUserPage";
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserRegistrationForm form=new UserRegistrationForm();
		String userName=request.getParameter("username");
		form.setUserName(userName);
		String email=request.getParameter("emailid");
		form.setEmail(email);
		String password=request.getParameter("password");
		form.setPassword(password);
		Long phoneNumber=Long.parseLong(request.getParameter("phonenumber"));
		form.setPhoneNumber(phoneNumber);
		
		UserService userService=FactoryService.INSTANCE.getUserService();
		
		try {
			userService.createUser(form);
			response.sendRedirect(LOGIN_USERSUCESS_PAGE);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
