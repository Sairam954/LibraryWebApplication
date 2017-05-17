package com.epam.finalweb.dao;

import java.util.List;

import com.epam.finalweb.domain.Book;
import com.epam.finalweb.exception.DaoException;

public interface BookDao {

	List<Book> getBooksOfUser(int userId,String language) throws DaoException;
	List<Book> getSeacrhedBook(int userId,String text) throws DaoException;
	
	
}
