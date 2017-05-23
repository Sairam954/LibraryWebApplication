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

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserRegistrationForm form = new UserRegistrationForm();
		String userName = request.getParameter("username");
		form.setUserName(userName);
		String email = request.getParameter("emailid");
		form.setEmail(email);
		String password = request.getParameter("password");
		form.setPassword(password);
		Long phoneNumber = Long.parseLong(request.getParameter("phonenumber"));
		form.setPhoneNumber(phoneNumber);

		UserService userService = FactoryService.INSTANCE.getUserService();

		try {
			userService.createUser(form);
			HttpSession session = request.getSession();

			session.setAttribute("userName", userName);
			session.setAttribute("userType", UserType.USER);
			session.setAttribute("isLoged", true);
			session.setAttribute("newUser", true);
			response.sendRedirect(LOGIN_USERSUCESS_PAGE);
		} catch (ServiceException e) {
			request.setAttribute("errorMessage", "Email is already a existing user");

			request.getRequestDispatcher("index.jsp").forward(request, response);

		} catch (ValidationException e) {
			request.setAttribute("errorMessage", "Please Fill the details properly");

			request.getRequestDispatcher("index.jsp").forward(request, response);

		}

	}

}
