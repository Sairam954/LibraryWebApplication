package com.epam.finalweb.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.finalweb.command.Command;
import com.epam.finalweb.domain.Book;

import com.epam.finalweb.service.BookService;
import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.factory.FactoryService;

public class AllBookAdminCommand implements Command{
	private static final String LANGUAGE="language";
	private static final String ADMINBOOKPAGE="adminBookPage";
	private static Logger LOG = Logger.getLogger(AllBookAdminCommand.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		String locale = (String) session.getAttribute(LANGUAGE);

		BookService bookService = FactoryService.INSTANCE.getBookService();
		List<Book> books = null;
		try {
			
			
			
			
			
				books = bookService.getAllBookAdmin(locale);
				request.setAttribute("allBooks", books);
				request.getRequestDispatcher(ADMINBOOKPAGE).forward(request, response);
			}
		
		 catch (ServiceException e) {
			LOG.error("Service Exception", e);

		}

	}
		
	

	
	
	
}
