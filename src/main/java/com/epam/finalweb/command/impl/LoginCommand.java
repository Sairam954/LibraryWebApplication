package com.epam.finalweb.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
	
	private static final String LOGIN_USERSUCESS_PAGE="LoginSucessUserPage";
	private static final String LOGIN_ADMINSUCESS_PAGE="LoginSucessAdminPage";
	
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailId = request.getParameter("emailId");
		String password = request.getParameter("password");

		UserService userService = FactoryService.INSTANCE.getUserService();
		BookService bookService = FactoryService.INSTANCE.getBookService();
		User user = null;
		List<Book> books = new ArrayList<Book>();
		try {
			user = userService.getUser(emailId, password);

			HttpSession session = request.getSession();

			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userType", user.getUserType());
			session.setAttribute("isLoged", true);
			session.setMaxInactiveInterval(30 * 60);
			
			String locale = (String) session.getAttribute("language");
			

			
			books = bookService.getBookOfUser(user.getId(), locale);
			session.setAttribute("userId", user.getId());
			session.setAttribute("books", books);
			Cookie userName = new Cookie("user", user.getUserName());
			response.addCookie(userName);
			System.out.println(books.toString());
			
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

		}catch (ServiceException e) {
			LOG.error("Wrong Password ");
			request.setAttribute("errorMessage", "Wrong Password Please Enter Correct Password");

		} catch (ValidationException e) {
			request.setAttribute("errorMessage", "Email is  not valid ");
			
		}
		if (user == null) {

			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}

}
