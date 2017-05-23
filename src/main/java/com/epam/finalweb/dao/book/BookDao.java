package com.epam.finalweb.dao.book;

import java.util.List;

import com.epam.finalweb.dao.exception.DaoException;
import com.epam.finalweb.domain.Book;

public interface BookDao {

	List<Book> getBooksOfUser(int userId,String language) throws DaoException;
	List<Book> getSeacrhedBook(int userId,String text) throws DaoException;
	
	
}
