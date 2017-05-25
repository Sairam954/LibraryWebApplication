package com.epam.finalweb.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.finalweb.command.Command;
import com.epam.finalweb.service.BookService;
import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.factory.FactoryService;

public class DeleteBookCommand  implements Command{
	private static final String ADMIN_ALLBOOKPAGE="admin?commandName=allbookadmin";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId=Integer.parseInt(request.getParameter("bookId"));
		
		BookService bookService = FactoryService.INSTANCE.getBookService();
		try {
			bookService.deleteBook(bookId);
			request.getRequestDispatcher(ADMIN_ALLBOOKPAGE).forward(request, response);
			
			
		} catch (ServiceException e) {
			request.setAttribute("errorMessage","Could not delete the Book please try later" );
			request.getRequestDispatcher(ADMIN_ALLBOOKPAGE).forward(request, response);
			
		}
	}

}
