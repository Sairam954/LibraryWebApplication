package com.epam.finalweb.dao.book.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
	private static final String BOOK_ID="book_id";
	private static final String BOOK_TITLE="book_title";
	private static final String BOOK_AUTHOR="book_author";
	private static final String BOOK_TYPE="book_type";
	private static final String BOOK_LANGUAGE="book_language";
	private static final String	BOOK_DESCRIPTION="book_description";						
	private static final String USERS="users";
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
				book.setBookId(rs.getInt(BOOK_ID));
				book.setBookTitle(rs.getString(BOOK_TITLE));
				book.setBookAuthor(rs.getString(BOOK_AUTHOR));
				book.setBookType(BookType.valueOf(rs.getString(BOOK_TYPE).toUpperCase()));
				book.setBookLanguage(rs.getString(BOOK_LANGUAGE));
				book.setDescription(rs.getString(BOOK_DESCRIPTION));

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
				book.setBookId(rs.getInt(BOOK_ID));
				book.setBookTitle(rs.getString(BOOK_TITLE));
				book.setBookAuthor(rs.getString(BOOK_AUTHOR));
				book.setBookType(BookType.valueOf(rs.getString(BOOK_TYPE).toUpperCase()));
				book.setBookLanguage(rs.getString(BOOK_LANGUAGE));
				book.setDescription(rs.getString(BOOK_DESCRIPTION));

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
	public List<UserBook> getAllBook(String language,int userId) throws DaoException {

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
				
				book.setBookId(rs.getInt(BOOK_ID));
				book.setBookTitle(rs.getString(BOOK_TITLE));
				book.setBookAuthor(rs.getString(BOOK_AUTHOR));
				book.setBookType(BookType.valueOf(rs.getString(BOOK_TYPE).toUpperCase()));
				book.setBookLanguage(rs.getString(BOOK_LANGUAGE));
				book.setDescription(rs.getString(BOOK_DESCRIPTION));
				String users=rs.getString(USERS);
			
				if(users!=null){
					
					userBook = new UserBook(book,rs.getString(USERS),Arrays.asList(users.split(",")).contains(userId+""));					
				}
				else
				{
					userBook = new UserBook(book,rs.getString(USERS),false);					
					
				}
				
				
				
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

	@Override
	public void createBook(Book book) throws DaoException {
		Connection con = null;
		
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement st = null;
		try {
			
			con = connectionPool.getConnection();
			st = con.prepareStatement(BookDaoUtil.CREATE_BOOK);
			
			st.setString(1,book.getBookTitle());
			st.setString(2,book.getBookAuthor());
			st.setString(3,book.getBookType().toString());
			st.setString(4,book.getBookLanguage());
			st.setString(5,book.getDescription());
			
			
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
	public List<Book> getAllBookAdmin(String language) throws DaoException {

		Connection con = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement st = null;
		Book book;
	
		List<Book> books = null;
		try {
			con = connectionPool.getConnection();
			st = con.prepareStatement(BookDaoUtil.ALL_BOOKADMIN);

			st.setString(1, language);
			ResultSet rs = st.executeQuery();
			books = new ArrayList<Book>();

			while (rs.next()) {
				book = new Book();
				
				book.setBookId(rs.getInt(BOOK_ID));
				book.setBookTitle(rs.getString(BOOK_TITLE));
				book.setBookAuthor(rs.getString(BOOK_AUTHOR));
				book.setBookType(BookType.valueOf(rs.getString(BOOK_TYPE).toUpperCase()));
				book.setBookLanguage(rs.getString(BOOK_LANGUAGE));
				book.setDescription(rs.getString(BOOK_DESCRIPTION));
				
				
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

	@Override
	public void deleteBook(int bookId) throws DaoException {
		Connection con = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement st = null;
		try {
			con = connectionPool.getConnection();
			st = con.prepareStatement(BookDaoUtil.DELETE_BOOK);
			st.setInt(1,bookId);
			st.executeUpdate();
			
		}catch (SQLException e) {
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
	

		
	
	}

	@Override
	public void updateBook(Book book) throws DaoException {
Connection con = null;
		
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement st = null;
		try {
			
			con = connectionPool.getConnection();
			st = con.prepareStatement(BookDaoUtil.UPDATE_BOOK);
			
			st.setString(1,book.getBookTitle());
			st.setString(2,book.getBookAuthor());
			st.setString(3,book.getBookType().toString());
			st.setString(4,book.getBookLanguage());
			st.setString(5,book.getDescription());
			st.setInt(6, book.getBookId());
			
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
