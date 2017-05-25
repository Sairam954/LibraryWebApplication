package com.epam.finalweb.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.finalweb.command.Command;
import com.epam.finalweb.domain.UserRegistrationForm;
import com.epam.finalweb.domain.UserType;
import com.epam.finalweb.service.UserService;

import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.exception.ValidationException;
import com.epam.finalweb.service.factory.FactoryService;

public class RegisterCommand implements Command {
	private static final String LOGIN_USERSUCESS_PAGE = "LoginSucessUserPage";
	private static final String USERNAME="userName";
	private static final String EMAILID="emailid";
	private static final String PASSWORD="password";
	private static final String PHONENUMBER="phonenumber";
	private static final String USERTYPE="userType";
	private static final String ISLOGED="isLoged";
	private static final String NEWUSER="newUser";
	private static final String ERRORMESSAGE="errorMessage";
	private static final String INDEXPAGE="index.jsp"; 
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserRegistrationForm form = new UserRegistrationForm();
		String userName = request.getParameter("username");
		form.setUserName(userName);
		String email = request.getParameter(EMAILID);
		form.setEmail(email);
		String password = request.getParameter(PASSWORD);
		form.setPassword(password);
		Long phoneNumber = Long.parseLong(request.getParameter(PHONENUMBER));
		form.setPhoneNumber(phoneNumber);

		UserService userService = FactoryService.INSTANCE.getUserService();

		try {
			userService.createUser(form);
			HttpSession session = request.getSession();

			session.setAttribute(USERNAME, userName);
			session.setAttribute(USERTYPE, UserType.USER);
			session.setAttribute(ISLOGED, true);
			session.setAttribute(NEWUSER, true);
			response.sendRedirect(LOGIN_USERSUCESS_PAGE);
		} catch (ServiceException e) {
			request.setAttribute(ERRORMESSAGE, "Email is already a existing user");

			request.getRequestDispatcher(INDEXPAGE).forward(request, response);

		} catch (ValidationException e) {
			request.setAttribute(ERRORMESSAGE, "Please Fill the details properly");

			request.getRequestDispatcher(INDEXPAGE).forward(request, response);

		}

	}

}
