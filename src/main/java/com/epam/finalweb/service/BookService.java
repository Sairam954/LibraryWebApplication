package com.epam.finalweb.service;

import java.util.List;

import com.epam.finalweb.domain.Book;
import com.epam.finalweb.domain.UserBook;
import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.exception.ValidationException;

public interface BookService {

	 List<Book> getBookOfUser(int userId,String language) throws ServiceException;
	 List<Book> searchBook(String searchText,int userId) throws ServiceException;
	 List<Book> searchAllBook(String searchText) throws ServiceException;
	 List<UserBook> getAllBook(String language,int userId) throws ServiceException;
	 List<Book> getAllBookAdmin(String language) throws ServiceException;
	 void addBook(int bookId,int userId) throws ServiceException;
	 void removeBook(int bookId,int userId) throws ServiceException;
	 void createBook(Book book) throws ServiceException,ValidationException;
	 void deleteBook(int bookId) throws ServiceException;
	 void updateBook(Book book) throws ServiceException, ValidationException;

}
