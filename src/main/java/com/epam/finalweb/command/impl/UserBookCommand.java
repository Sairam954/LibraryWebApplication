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

public class UserBookCommand implements Command {
private static final Logger LOG=Logger.getLogger(UserBookCommand.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("userId");
		String locale = (String) session.getAttribute("language");
		BookService bookService=FactoryService.INSTANCE.getBookService();
		List<Book> books=null;
		
		try {
			books = bookService.getBookOfUser(userId, locale);
			if (books.isEmpty()) {
				session.setAttribute("libraryEmpty", "You Dont have anything in Your Library");
			} else {
				session.removeAttribute("libraryEmpty");
			}
			request.setAttribute("userBooks", books);
			request.getRequestDispatcher("userBookPage").forward(request, response);
		} catch (ServiceException e) {
			LOG.error("Service Exception",e);
		}
		
	}

}
