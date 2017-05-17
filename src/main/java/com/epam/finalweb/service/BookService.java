package com.epam.finalweb.service;

import java.util.List;

import com.epam.finalweb.domain.Book;
import com.epam.finalweb.exception.ServiceException;

public interface BookService {

	public List<Book> getBookOfUser(int userId,String language) throws ServiceException;
	public List<Book> searchBook(String searchText,int userId) throws ServiceException;
	
}
