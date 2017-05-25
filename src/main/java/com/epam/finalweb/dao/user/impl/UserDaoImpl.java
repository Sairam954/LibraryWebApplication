package com.epam.finalweb.dao.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.log4j.Logger;

import com.epam.finalweb.dao.ConnectionPool;
import com.epam.finalweb.dao.exception.DaoException;
import com.epam.finalweb.dao.exception.PoolException;
import com.epam.finalweb.dao.user.UserDao;
import com.epam.finalweb.dao.user.UserDaoUtil;
import com.epam.finalweb.domain.User;
import com.epam.finalweb.domain.UserRegistrationForm;
import com.epam.finalweb.domain.UserType;


public class UserDaoImpl implements UserDao {

	private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);
	private static final String USER_ID="user_id";
	private static final String USER_NAME="user_name";
	private static final String USER_EMAIL="user_email";
	private static final String USER_TYPE="user_type";
	private static final String USER_PHONENUMBER="user_phonenumber";
	public User getUser(String emailId, String password) throws DaoException {
		Connection con = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		PreparedStatement st = null;

		try {
			con = connectionPool.getConnection();

			st = con.prepareStatement(UserDaoUtil.USER_DETAILS);
			st.setString(1, emailId);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			rs.next();
			User user = new User();
			user.setId(rs.getInt(USER_ID));
			user.setUserName(rs.getString(USER_NAME));
			user.setUserEmail(rs.getString(USER_EMAIL));

			user.setUserType(UserType.valueOf(rs.getString(USER_TYPE).toUpperCase()));
			user.setPhoneNumber(rs.getLong(USER_PHONENUMBER));
			return user;
		} catch (SQLException e) {
			throw new DaoException("Cannot create Prepared Statement", e);
		} catch (PoolException e) {
			throw new DaoException("Cannot get Connection ", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					LOG.error("Cannot close Prepared Statement", e);
				}

			}
			try {
				connectionPool.returnConnection(con);
			} catch (PoolException e) {
				LOG.error("Cannot return the Connection", e);
			}
		}

	}

	public boolean validateUserEmailId(String emailId) throws DaoException {
		Connection con = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		PreparedStatement st = null;
		try {
			con = connectionPool.getConnection();
			st = con.prepareStatement(UserDaoUtil.EMAIL_VALIDATE);
			st.setString(1, emailId);

			ResultSet resultSet = st.executeQuery();
			resultSet.next();
			int validEmail = resultSet.getInt(1);
			return validEmail > 0;

		} catch (SQLException e) {
			throw new DaoException("Cannot create  prepared statement", e);
		} catch (PoolException e) {
			throw new DaoException("Cannot get  Connection from ConnectionPool", e);

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

	public void createUser(UserRegistrationForm user) throws DaoException {
		Connection con = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		PreparedStatement st = null;
		try {
			con = connectionPool.getConnection();
			st = con.prepareStatement(UserDaoUtil.CREATE_USER);
			st.setString(1, user.getUserName());
			st.setString(2, user.getEmail());
			st.setString(3, user.getPassword());
			st.setString(4, UserType.USER.toString());
			st.setLong(5, user.getPhoneNumber());
			st.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("Cannot create  prepared statement", e);
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

	

}
