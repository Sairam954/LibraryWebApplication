package com.epam.finalweb.dao.book.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.finalweb.dao.ConnectionPool;
import com.epam.finalweb.dao.book.BookDao;
import com.epam.finalweb.dao.book.BookDaoUtil;
import com.epam.finalweb.dao.exception.DaoException;
import com.epam.finalweb.dao.exception.PoolException;
import com.epam.finalweb.domain.Book;
import com.epam.finalweb.domain.BookType;
import com.epam.finalweb.domain.UserBook;

public class BookDaoImpl implements BookDao {

	private static final Logger LOG = Logger.getLogger(BookDaoImpl.class);

	public List<Book> getBooksOfUser(int userId, String language) throws DaoException {

		Connection con = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement st = null;
		Book book;
		List<Book> books = null;
		try {
			con = connectionPool.getConnection();
			st = con.prepareStatement(BookDaoUtil.BOOKS_OFUSER);
			st.setInt(1, userId);
			st.setString(2, language);
			ResultSet rs = st.executeQuery();
			books = new ArrayList<Book>();
			while (rs.next()) {
				book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setBookTitle(rs.getString("book_title"));
				book.setBookAuthor(rs.getString("book_author"));
				book.setBookType(BookType.valueOf(rs.getString("book_type").toUpperCase()));
				book.setBookLanguage(rs.getString("book_language"));
				book.setDescription(rs.getString("book_description"));

				books.add(book);
			}

		} catch (SQLException e) {
			throw new DaoException("Cannot create Prepared Statement", e);
		} catch (PoolException e) {
			throw new DaoException("Cannot get connection from pool", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					LOG.error("Cannot close Prepared Statement");
				}

			}
			try {
				connectionPool.returnConnection(con);
			} catch (PoolException e) {
				LOG.error("Cannot return the Connection", e);
			}

		}
		return books;

	}

	public List<Book> getSeacrhedBook(int userId, String text) throws DaoException {
		Connection con = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		PreparedStatement st = null;
		Book book;
		List<Book> books = null;
		try {
			con = connectionPool.getConnection();
			st = con.prepareStatement(BookDaoUtil.BOOK_SEARCH);
			st.setString(1, text);

			st.setInt(2, userId);
			ResultSet rs = st.executeQuery();
			books = new ArrayList<Book>();
			while (rs.next()) {

				book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setBookTitle(rs.getString("book_title"));
				book.setBookAuthor(rs.getString("book_author"));
				book.setBookType(BookType.valueOf(rs.getString("book_type").toUpperCase()));
				book.setBookLanguage(rs.getString("book_language"));
				book.setDescription(rs.getString("book_description"));

				books.add(book);

			}
			return books;

		} catch (SQLException e) {
			throw new DaoException("Cannot create Prepared Statement", e);
		} catch (PoolException e) {
			throw new DaoException("Cannot get Connection from the Pool", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					LOG.error("Cannot close Prepared Statement");
				}

			}
			try {
				connectionPool.returnConnection(con);
			} catch (PoolException e) {
				LOG.error("Cannot return the Connection", e);
			}
		}

	}

	@Override
	public List<UserBook> getAllBook(String language) throws DaoException {

		Connection con = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement st = null;
		Book book;
		UserBook userBook;
		List<UserBook> books = null;
		try {
			con = connectionPool.getConnection();
			st = con.prepareStatement(BookDaoUtil.ALL_BOOK);

			st.setString(1, language);
			ResultSet rs = st.executeQuery();
			books = new ArrayList<UserBook>();

			while (rs.next()) {
				book = new Book();
				userBook = new UserBook();
				book.setBookId(rs.getInt("book_id"));
				book.setBookTitle(rs.getString("book_title"));
				book.setBookAuthor(rs.getString("book_author"));
				book.setBookType(BookType.valueOf(rs.getString("book_type").toUpperCase()));
				book.setBookLanguage(rs.getString("book_language"));
				book.setDescription(rs.getString("book_description"));
				userBook.setBook(book);
				userBook.setUserList(rs.getString("users"));
				books.add(userBook);
			}

		} catch (SQLException e) {
			throw new DaoException("Cannot create Prepared Statement", e);
		} catch (PoolException e) {
			throw new DaoException("Cannot get connection from pool", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					LOG.error("Cannot close Prepared Statement");
				}

			}
			try {
				connectionPool.returnConnection(con);
			} catch (PoolException e) {
				LOG.error("Cannot return the Connection", e);
			}

		}
		return books;
	}

	@Override
	public void addBook(int bookId, int userId) throws DaoException {
		Connection con = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement st = null;
		try {
			System.out.println("userId" + userId + "bookId" + bookId);
			con = connectionPool.getConnection();
			st = con.prepareStatement(BookDaoUtil.ADD_BOOK);
			st.setInt(1, bookId);
			st.setInt(2, userId);
			st.executeUpdate();

		} catch (PoolException e) {
			throw new DaoException("Cannot get connection from pool", e);
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
			try {
				connectionPool.returnConnection(con);
			} catch (PoolException e) {
				LOG.error("Cannot return the Connection", e);
			}

		}

	}

	@Override
	public void removeBook(int bookId, int userId) throws DaoException {
		Connection con = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement st = null;
		try {
			System.out.println("userId" + userId + "bookId" + bookId);
			con = connectionPool.getConnection();
			st = con.prepareStatement(BookDaoUtil.REMOVE_BOOK);
			st.setInt(1, bookId);
			st.setInt(2, userId);
			st.executeUpdate();

		} catch (PoolException e) {
			throw new DaoException("Cannot get connection from pool", e);
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
			try {
				connectionPool.returnConnection(con);
			} catch (PoolException e) {
				LOG.error("Cannot return the Connection", e);
			}

		}

	}

}
