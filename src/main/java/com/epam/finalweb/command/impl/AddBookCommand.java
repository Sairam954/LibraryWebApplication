package com.epam.finalweb.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.finalweb.command.Command;
import com.epam.finalweb.service.BookService;
import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.factory.FactoryService;

public class AddBookCommand implements Command {
	private static final Logger LOG = Logger.getLogger(AddBookCommand.class);
	private static final String ALLBOOKPAGE = "allBookPage";
	private static final String ALLBOOK="user?commandName=allbook";
	private static final String ERRORMESSAGE="errorMessage";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userID = (String) session.getAttribute("userId");
		int userId = Integer.parseInt(userID);
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		BookService bookService = FactoryService.INSTANCE.getBookService();
		try {
			bookService.addBook(bookId, userId);

			response.sendRedirect(ALLBOOK);
		} catch (ServiceException e) {
			LOG.error("Could Not add book to user library", e);

			request.setAttribute(ERRORMESSAGE, "Book not added please try later");
			request.getRequestDispatcher(ALLBOOKPAGE).forward(request, response);

		}

	}

}
