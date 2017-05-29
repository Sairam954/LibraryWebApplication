package com.epam.finalweb.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.finalweb.command.Command;
import com.epam.finalweb.domain.Book;
import com.epam.finalweb.domain.User;
import com.epam.finalweb.domain.UserType;
import com.epam.finalweb.service.BookService;
import com.epam.finalweb.service.UserService;
import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.exception.ValidationException;
import com.epam.finalweb.service.factory.FactoryService;

public class LoginCommand implements Command {
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	private static final String LOGIN_USERSUCESS_PAGE = "LoginSucessUserPage";
	private static final String LOGIN_ADMINSUCESS_PAGE = "LoginSucessAdminPage";
	private static final String EMAILID = "emailId";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "userName";
	private static final String USERTYPE = "userType";
	private static final String ISLOGED = "isLoged";
	private static final String LANGUAGE = "language";
	private static final String USERID = "userId";
	private static final String ERRORMESSAGE = "errorMessage";
	private static final String INDEXPAGE = "index.jsp";

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailId = request.getParameter(EMAILID);
		String password = request.getParameter(PASSWORD);

		UserService userService = FactoryService.INSTANCE.getUserService();
		BookService bookService = FactoryService.INSTANCE.getBookService();
		User user = null;
		List<Book> books = new ArrayList<Book>();
		try {
			user = userService.getUser(emailId, password);

			HttpSession session = request.getSession();

			session.setAttribute(USERNAME, user.getUserName());
			session.setAttribute(USERTYPE, user.getUserType().toString().toLowerCase());
			session.setAttribute(USERID, String.valueOf(user.getId()));
			session.setAttribute(ISLOGED, true);
			session.setMaxInactiveInterval(30 * 60);

			String locale = (String) session.getAttribute(LANGUAGE);

			books = bookService.getBookOfUser(user.getId(), locale);
			
			session.setAttribute("books", books);

			

			if (books.isEmpty()) {
				session.setAttribute("libraryEmpty", "You Dont have anything in Your Library");
			} else {
				session.removeAttribute("libraryEmpty");
			}
			if (user.getUserType() == UserType.USER) {
				response.encodeRedirectURL("LoginSucessUserPage.jsp");

				response.sendRedirect(LOGIN_USERSUCESS_PAGE);
			} else {

				response.encodeRedirectURL("LoginSucessAdminPage.jsp");

				response.sendRedirect(LOGIN_ADMINSUCESS_PAGE);
			}

		} catch (ServiceException e) {
			LOG.error("Wrong Password ");
			request.setAttribute(ERRORMESSAGE, "Wrong Password Please Enter Correct Password");

		} catch (ValidationException e) {
			request.setAttribute(ERRORMESSAGE, "Email is  not valid ");

		}
		if (user == null) {

			request.getRequestDispatcher(INDEXPAGE).forward(request, response);
		}

	}

}
