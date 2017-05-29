package com.epam.finalweb.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.finalweb.command.Command;
import com.epam.finalweb.domain.User;
import com.epam.finalweb.service.UserService;
import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.factory.FactoryService;

public class AllUserCommand implements Command{

	private static final Logger LOG=Logger.getLogger(AllUserCommand.class);
	private static final String ALL_USERPAGE="allUserAdminPage";
	private static final String ALLUSERS="allusers";
	private static final String LOGIN_ADMINSUCESS_PAGE = "LoginSucessAdminPage";
	private static final String ERRORMESSAGE="errorMessage";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users=null;
		UserService userService=FactoryService.INSTANCE.getUserService();
		try {
			users=userService.getAllUser();
			request.setAttribute(ALLUSERS,users);
			request.getRequestDispatcher(ALL_USERPAGE).forward(request, response);
		} catch (ServiceException e) {
			LOG.error("Cannot get User Details",e);
			request.setAttribute(ERRORMESSAGE,"Cannot get User Details Please Try Again");
			request.getRequestDispatcher(LOGIN_ADMINSUCESS_PAGE).forward(request, response);
		}
	}

}
