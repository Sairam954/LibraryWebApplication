package com.epam.finalweb.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.finalweb.command.Command;
import com.epam.finalweb.domain.User;

import com.epam.finalweb.service.UserService;
import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.factory.FactoryService;

public class AccountDetailsCommand implements Command {

	private static final Logger LOG = Logger.getLogger(AccountDetailsCommand.class);
	private static final String ADMIN = "admin";
	private static final String USER = "user";
	private static final String ACCOUNT_USERPAGE = "accountPage";
	private static final String ACCOUNT_ADMINPAGE = "accountAdminPage";
	private static final String LOGIN_USERSUCESS_PAGE = "LoginSucessUserPage";
	private static final String LOGIN_ADMINSUCESS_PAGE = "LoginSucessAdminPage";
	private static final String USERTYPE = "userType";
	private static final String ERRORMESSAGE = "errorMessage";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		String userID = (String) session.getAttribute("userId");

		int userId = Integer.parseInt(userID);
		String userType = (String) session.getAttribute(USERTYPE);
		UserService userService = FactoryService.INSTANCE.getUserService();
		try {
			User user = userService.getUser(userId);

			request.setAttribute(USER, user);
			if (userType.equalsIgnoreCase(ADMIN)) {
				request.getRequestDispatcher(ACCOUNT_ADMINPAGE).forward(request, response);

			} else {
				request.getRequestDispatcher(ACCOUNT_USERPAGE).forward(request, response);

			}
		
		} catch (ServiceException e) {
			LOG.error("Service Exception", e);
			if (userType.equals(ADMIN)) {

				request.setAttribute(ERRORMESSAGE, "Sorry Something wen wrong Please try later");
				request.getRequestDispatcher(LOGIN_ADMINSUCESS_PAGE).forward(request, response);

			} else {
				request.setAttribute(ERRORMESSAGE, "Sorry Something wen wrong Please try later");
				request.getRequestDispatcher(LOGIN_USERSUCESS_PAGE).forward(request, response);

			}

		}

	}

}
