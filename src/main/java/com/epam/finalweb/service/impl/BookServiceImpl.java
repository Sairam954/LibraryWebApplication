package com.epam.finalweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.epam.finalweb.dao.book.BookDao;
import com.epam.finalweb.dao.factory.FactoryDao;
import com.epam.finalweb.domain.Book;
import com.epam.finalweb.exception.DaoException;
import com.epam.finalweb.exception.ServiceException;
import com.epam.finalweb.service.BookService;

public class BookServiceImpl implements BookService{

	public List<Book> getBookOfUser(int userId, String language) throws ServiceException {
		BookDao bookDao=FactoryDao.INSTANCE.getBookDao();
		try {
			return bookDao.getBooksOfUser(userId, language);
		} catch (DaoException e) {
			throw new ServiceException("Service Cannot get books",e);
		}
		
		
	}

	public List<Book> searchBook(String searchText,int userId) throws ServiceException {
		BookDao bookDao=FactoryDao.INSTANCE.getBookDao();
		try {
			
			List<Book> searchedBooks=new ArrayList<Book>();
			searchedBooks=bookDao.getSeacrhedBook(userId, searchText);
			return searchedBooks;
			
			
		} catch (DaoException e) {
			throw new ServiceException("Cannot get Books of user",e);
		}
		
		
	}

	

}
