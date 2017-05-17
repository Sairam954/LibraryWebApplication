package com.epam.finalweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.finalweb.dao.BookDao;
import com.epam.finalweb.domain.Book;
import com.epam.finalweb.domain.BookType;

import com.epam.finalweb.exception.DaoException;
import com.epam.finalweb.util.JDBCConnection;

public class BookDaoImpl implements BookDao {

	private static final String BOOKS_OFUSER = "select book_id,book_title,book_author,"
			+ "book_type,book_language,book_description from book  join "
			+ "book_has_user_details on book.book_id=book_has_user_details.book_book_id  " + "join user_details on "
			+ "book_has_user_details.user_details_user_id=user_details.user_id where user_id=?"
			+ " and book_language=?";

	private static final String BOOK_SEARCH = "select book_id,book_title,book_author,book_type,"
			+ "book_language,book_description from (SELECT * FROM book WHERE MATCH"
			+ " (book_title,book_author,book_description) AGAINST (?)) as"
			+ " t join book_has_user_details on t.book_id=book_has_user_details.book_book_id " + 
			" join user_details"
			+ " on book_has_user_details.user_details_user_id=user_details.user_id where user_id=?";
	private static final Logger LOG = Logger.getLogger(BookDaoImpl.class);

	public List<Book> getBooksOfUser(int userId, String language) throws DaoException {
		Connection con = JDBCConnection.getConnection();

		PreparedStatement st = null;
		Book book;
		List<Book> books = new ArrayList<Book>();
		try {

			st = con.prepareStatement(BOOKS_OFUSER);
			st.setInt(1, userId);
			st.setString(2, language);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				book = new Book(rs.getInt("book_id"), rs.getString("book_title"), rs.getString("book_author"),
						BookType.valueOf(rs.getString("book_type").toUpperCase()), rs.getString("book_language"),
						rs.getString("book_description"));

				books.add(book);
			}
			return books;

		} catch (SQLException e) {
			throw new DaoException("Cannot create Prepared Statement", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					LOG.error("Cannot close Prepared Statement");
				}

			}
			JDBCConnection.closeConnection();
		}

	}

	public List<Book> getSeacrhedBook(int userId, String text) throws DaoException {
		Connection con = JDBCConnection.getConnection();

		PreparedStatement st = null;
		Book book;
		List<Book> books = new ArrayList<Book>();
		try {

			st = con.prepareStatement(BOOK_SEARCH);
			st.setString(1, text);

			st.setInt(2, userId);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				book = new Book(rs.getInt("book_id"), rs.getString("book_title"), rs.getString("book_author"),
						BookType.valueOf(rs.getString("book_type").toUpperCase()), rs.getString("book_language"),
						rs.getString("book_description"));

				books.add(book);
			}
			return books;

		} catch (SQLException e) {
			throw new DaoException("Cannot create Prepared Statement", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					LOG.error("Cannot close Prepared Statement");
				}

			}
			JDBCConnection.closeConnection();
		}

	}

}
