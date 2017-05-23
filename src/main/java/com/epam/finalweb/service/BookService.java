package com.epam.finalweb.service;

import java.util.List;

import com.epam.finalweb.domain.Book;
import com.epam.finalweb.domain.UserBook;
import com.epam.finalweb.service.exception.ServiceException;

public interface BookService {

	 List<Book> getBookOfUser(int userId,String language) throws ServiceException;
	 List<Book> searchBook(String searchText,int userId) throws ServiceException;
	 List<UserBook> getAllBook(String language) throws ServiceException;
	 void addBook(int bookId,int userId) throws ServiceException;
	 void removeBook(int bookId,int userId) throws ServiceException;
}
