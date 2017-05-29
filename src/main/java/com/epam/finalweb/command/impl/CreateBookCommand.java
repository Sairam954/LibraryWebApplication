package com.epam.finalweb.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.finalweb.command.Command;
import com.epam.finalweb.domain.Book;
import com.epam.finalweb.domain.BookType;
import com.epam.finalweb.service.BookService;
import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.exception.ValidationException;
import com.epam.finalweb.service.factory.FactoryService;

public class CreateBookCommand implements Command {

	private static final String BOOK_TITLE = "bookTitle";
	private static final String BOOK_AUTHOR = "bookAuthor";
	private static final String BOOK_TYPE = "bookType";
	private static final String BOOK_LANGUAGE = "bookLanguage";
	private static final String BOOK_DESCRIPTION = "description";
	private static final String ADMIN_ALLBOOKPAGE = "admin?commandName=allbookadmin";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book newBook = new Book();
		String bookTitle = request.getParameter(BOOK_TITLE);

		String bookAuthor = request.getParameter(BOOK_AUTHOR);

		String type = request.getParameter(BOOK_TYPE);

		BookType bookType = BookType.valueOf(type.toUpperCase());

		String bookLanguage = request.getParameter(BOOK_LANGUAGE);
		String description = request.getParameter(BOOK_DESCRIPTION);
		newBook.setBookTitle(bookTitle);
		newBook.setBookAuthor(bookAuthor);
		newBook.setBookType(bookType);
		newBook.setBookLanguage(bookLanguage);
		newBook.setDescription(description);
		BookService bookService = FactoryService.INSTANCE.getBookService();
		try {
			bookService.createBook(newBook);
			response.sendRedirect(ADMIN_ALLBOOKPAGE);
		} catch (ServiceException e) {

			request.setAttribute("errorMessage", "Could not create the Book please try later");
			request.getRequestDispatcher(ADMIN_ALLBOOKPAGE).forward(request, response);

		} catch (ValidationException e) {
			request.setAttribute("errorMessage", "Please Fill all fields of Book");
			request.getRequestDispatcher(ADMIN_ALLBOOKPAGE).forward(request, response);

		}

	}

}
