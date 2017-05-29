package com.epam.finalweb.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.finalweb.command.Command;
import com.epam.finalweb.domain.User;
import com.epam.finalweb.service.UserService;
import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.exception.ValidationException;
import com.epam.finalweb.service.factory.FactoryService;

public class UpdateUserCommand implements Command {

	private static final String USERNAME = "username";
	private static final String PHONENUMBER = "phonenumber";
	private static final String USERTYPE = "userType";
	private static final String OLDPASSWORD = "oldpassword";
	private static final String NEWPASSWORD = "newpassword";
	private static final String ERRORMESSAGE = "errorMessage";
	private static final String USERID = "userId";
	private static final String ACCOUNT_DETAILSPAGE = "user?commandName=accountDetails";
	private static final String ACCOUNT_DETAILSADMINPAGE = "admin?commandName=accountDetails";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String userId = (String) session.getAttribute(USERID);
		String userType = (String) session.getAttribute(USERTYPE);
		User user = new User();
		String userName = request.getParameter(USERNAME);
		user.setUserName(userName);
		user.setId(Integer.parseInt(userId));
		String oldPassword = request.getParameter(OLDPASSWORD);
		String newPassword = request.getParameter(NEWPASSWORD);

		Long phoneNumber = Long.parseLong(request.getParameter(PHONENUMBER));
		user.setPhoneNumber(phoneNumber);

		UserService userService = FactoryService.INSTANCE.getUserService();

		try {
			userService.updateUser(user, oldPassword, newPassword);
			session.setAttribute("userName",user.getUserName());
			if (userType.equalsIgnoreCase("admin")) {
				response.sendRedirect(ACCOUNT_DETAILSADMINPAGE);

			} else {
				response.sendRedirect(ACCOUNT_DETAILSPAGE);
			}
		} catch (ServiceException e) {
			request.setAttribute(ERRORMESSAGE, "Cannot Update User Account Please Try again ");
			if (userType.equalsIgnoreCase("admin")) {
				request.getRequestDispatcher(ACCOUNT_DETAILSADMINPAGE).forward(request, response);
			} else {
				request.getRequestDispatcher(ACCOUNT_DETAILSPAGE).forward(request, response);
			}

		} catch (ValidationException e) {

			request.setAttribute(ERRORMESSAGE, "User Old Password didnot match Please Enter Correct Password ");
			if (userType.equalsIgnoreCase("admin")) {
				request.getRequestDispatcher(ACCOUNT_DETAILSADMINPAGE).forward(request, response);
			} else {
				request.getRequestDispatcher(ACCOUNT_DETAILSPAGE).forward(request, response);
			}
		}

	}

}
