package com.epam.finalweb.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.finalweb.command.Command;
import com.epam.finalweb.domain.User;
import com.epam.finalweb.domain.UserType;
import com.epam.finalweb.exception.ServiceEmailNotValidException;
import com.epam.finalweb.exception.ServiceEmptyFieldException;
import com.epam.finalweb.exception.ServiceException;

import com.epam.finalweb.service.UserService;
import com.epam.finalweb.service.factory.FactoryService;

public class LoginCommand implements Command {
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailId = request.getParameter("emailId");
		String password = request.getParameter("password");

		UserService userService = FactoryService.INSTANCE.getUserService();
		User user = null;

		try {
			user = userService.getUser(emailId, password);

			HttpSession session = request.getSession();
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userType", user.getUserType());
			session.setAttribute("isLoged", true);
			session.setMaxInactiveInterval(30 * 60);

			Cookie userName = new Cookie("user", user.getUserName());
			response.addCookie(userName);

			if (user.getUserType() == UserType.USER) {
				response.encodeRedirectURL("LoginSucessUserPage.jsp");

				response.sendRedirect("LoginSucessUserPage");
			} else {

				response.encodeRedirectURL("LoginSucessAdminPage.jsp");
				response.sendRedirect("LoginSucessAdminPage");
			}

		} catch (ServiceEmptyFieldException e) {
			LOG.error("Password field cannot be empty or null");
			request.setAttribute("errorMessage", "Password field cannot be empty or null");
		} catch (ServiceEmailNotValidException e) {
			LOG.error("Email specified is not valid");
			request.setAttribute("errorMessage", "Email specified is not valid");

		} catch (ServiceException e) {
			LOG.error("Wrong Password ");
			request.setAttribute("errorMessage", "Wrong Password Please Enter Correct Password");

		}
		if (user == null) {

			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}

}
