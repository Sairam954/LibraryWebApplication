package com.epam.finalweb.controller;

import java.util.List;

import org.apache.log4j.PropertyConfigurator;

import com.epam.finalweb.dao.UserDao;
import com.epam.finalweb.dao.impl.BookDaoImpl;
import com.epam.finalweb.dao.impl.UserDaoImpl;
import com.epam.finalweb.domain.Book;
import com.epam.finalweb.domain.User;
import com.epam.finalweb.exception.DaoException;
import com.epam.finalweb.util.JDBCConnection;

public class App {

	public static void main(String[] args) {
		PropertyConfigurator.configure("src//main//resources//log4jconfig.properties");
		try {
			JDBCConnection.init();
		} catch (DaoException e) {

		}
		BookDaoImpl BookDao=new BookDaoImpl();
		try {
			List<Book> books=BookDao.getBooksOfUser(2,"hindi");
			for(Book book:books)
			{
				System.out.println(book.toString());
				
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
