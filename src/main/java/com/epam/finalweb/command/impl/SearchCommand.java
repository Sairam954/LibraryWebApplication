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

public class SearchCommand implements Command {

	private static final Logger LOG=Logger.getLogger(SearchCommand.class);
	private static final String SEARCH_PAGE="searchPage";
	private static final String SEARCH="search";
	private static final String SEARCHBOOKS="searchbooks";
	private static final String USERID="userId";
	
	
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchText=request.getParameter(SEARCH);
		
		HttpSession session = request.getSession(false);
		String userID=(String) session.getAttribute(USERID);
		int userId=Integer.parseInt(userID);
		BookService bookService=FactoryService.INSTANCE.getBookService();
		try {
			List<Book> searchedBook=bookService.searchBook(searchText, userId);
			
			request.setAttribute(SEARCHBOOKS,searchedBook);
			if(searchedBook.isEmpty())
			{
				session.setAttribute("notFound","Sorry Search Book Not Found");
			}
			else
			{
			
				session.removeAttribute("notFound");
				
			}
			request.getRequestDispatcher(SEARCH_PAGE).forward(request, response);
			
			
		} catch (ServiceException e) {
			LOG.error("Service Exception",e);
			
		}
		
		
		
	}

}
