package com.epam.finalweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.epam.finalweb.dao.book.BookDao;
import com.epam.finalweb.dao.exception.DaoException;
import com.epam.finalweb.dao.factory.FactoryDao;
import com.epam.finalweb.domain.Book;
import com.epam.finalweb.domain.UserBook;
import com.epam.finalweb.service.BookService;
import com.epam.finalweb.service.Validation;
import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.exception.ValidationException;

public class BookServiceImpl implements BookService {

	public List<Book> getBookOfUser(int userId, String locale) throws ServiceException {
		String language;
		if (locale.contains("hi_IN")) {
			language = "Hindi";
		} else {
			language = "English";
		}

		BookDao bookDao = FactoryDao.INSTANCE.getBookDao();
		try {
			return bookDao.getBooksOfUser(userId, language);
		} catch (DaoException e) {
			throw new ServiceException("Service Cannot get books", e);
		}

	}

	public List<Book> searchBook(String searchText, int userId) throws ServiceException {
		BookDao bookDao = FactoryDao.INSTANCE.getBookDao();
		try {

			List<Book> searchedBooks = new ArrayList<Book>();
			searchedBooks = bookDao.getSeacrhedBook(userId, searchText);
			return searchedBooks;

		} catch (DaoException e) {
			throw new ServiceException("Cannot get Books of user", e);
		}

	}

	@Override
	public List<UserBook> getAllBook(String locale,int userId) throws ServiceException {
		String language;
		if (locale.contains("hi_IN")) {
			language = "Hindi";
		} else {
			language = "English";
		}

		BookDao bookDao = FactoryDao.INSTANCE.getBookDao();
		try {

			List<UserBook> allBooks = null;
			allBooks = bookDao.getAllBook(language,userId);
			return allBooks;

		} catch (DaoException e) {
			throw new ServiceException("Cannot get Books of user", e);
		}
	}

	@Override
	public void addBook(int bookId, int userId) throws ServiceException {
		BookDao bookDao = FactoryDao.INSTANCE.getBookDao();

		try {
			bookDao.addBook(bookId, userId);
		} catch (DaoException e) {
			throw new ServiceException("Cannot add Book to user Library", e);
		}
	}

	@Override
	public void removeBook(int bookId, int userId) throws ServiceException {
		BookDao bookDao = FactoryDao.INSTANCE.getBookDao();

		try {
			bookDao.removeBook(bookId, userId);
		} catch (DaoException e) {
			throw new ServiceException("Cannot remove Book to user Library", e);
		}

	}

	@Override
	public void createBook(Book book) throws ServiceException, ValidationException {
	
		System.out.println("author"+book.getBookAuthor());
		Validation.validateNewBook(book);
		BookDao bookDao = FactoryDao.INSTANCE.getBookDao();
		try {
			bookDao.createBook(book);
		} catch (DaoException e) {
			throw new ServiceException("Cannot create Book to user Library", e);
		}

		
		
	}
	@Override
	public List<Book> getAllBookAdmin(String locale) throws ServiceException {
		String language;
		if (locale.contains("hi_IN")) {
			language = "Hindi";
		} else {
			language = "English";
		}

		BookDao bookDao = FactoryDao.INSTANCE.getBookDao();
		try {

			List<Book> allBooks = null;
			allBooks = bookDao.getAllBookAdmin(language);
			return allBooks;

		} catch (DaoException e) {
			throw new ServiceException("Cannot get Books of user", e);
		}
	}

	@Override
	public void deleteBook(int bookId) throws ServiceException {
		BookDao bookDao = FactoryDao.INSTANCE.getBookDao();
		try {
			bookDao.deleteBook(bookId);
		} catch (DaoException e) {
			throw new ServiceException("Cannot Delete Book ", e);
		}
	}

	@Override
	public void updateBook(Book book) throws ServiceException, ValidationException {
		Validation.validateNewBook(book);
		BookDao bookDao = FactoryDao.INSTANCE.getBookDao();
		try {
			bookDao.updateBook(book);
		} catch (DaoException e) {
			throw new ServiceException("Cannot create Book to user Library", e);
		}
	}

}
