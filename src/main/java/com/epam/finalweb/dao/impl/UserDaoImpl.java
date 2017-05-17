package com.epam.finalweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.finalweb.dao.UserDao;
import com.epam.finalweb.domain.User;
import com.epam.finalweb.domain.UserType;
import com.epam.finalweb.exception.DaoException;
import com.epam.finalweb.util.JDBCConnection;

public class UserDaoImpl implements UserDao {

	private static final String USER_DETAILS = "select * from user_details where user_email=? and user_password=? ";
	private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);
	private static final String EMAIL_VALIDATE = "select exists(select * from user_details where user_email=?)";
	private static final String USER_DETAILS_ALL = "select * from user_details  ";
	
	public User getUser(String emailId,String password) throws DaoException {
		Connection con = JDBCConnection.getConnection();

		PreparedStatement st = null;

		try {
			st = con.prepareStatement(USER_DETAILS);
			st.setString(1, emailId);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			rs.next();

			return new User(rs.getInt(1), rs.getString(2), rs.getString(4),
					UserType.valueOf(rs.getString(5).toUpperCase()), rs.getString(3), rs.getLong(6));

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

	public boolean validateUserEmailId(String emailId) throws DaoException {
		Connection con = JDBCConnection.getConnection();

		PreparedStatement st = null;
		try {
			st = con.prepareStatement(EMAIL_VALIDATE);
			st.setString(1, emailId);
		
			ResultSet resultSet = st.executeQuery();
			resultSet.next();
			int validEmail = resultSet.getInt(1);
			return (validEmail > 0) ? true : false;

		} catch (SQLException e) {
			throw new DaoException("Cannot create  prepared statement", e);
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
