package com.epam.finalweb.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;



import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import com.epam.finalweb.exception.DaoException;



public class JDBCConnection {

	private static String DB_NAME = "DB_NAME";
	private static String DB_USER = "DB_USER";
	private static String DB_PASSWORD = "DB_PASSWORD";
	private static String DB_URL = "DB_URL";
	private static String DB_CLASS = "DB_CLASS";
	
	private static Connection con=null;
	private static BasicDataSource ds;
	private static final Logger LOG = Logger.getLogger(JDBCConnection.class);

	
	public static void init() throws DaoException {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					"src//main//resources//jdbcconfig.properties");
			prop.load(input);
			LOG.info("DB_NAME" + prop.getProperty(DB_NAME));

			DB_NAME = prop.getProperty("DB_NAME");
			LOG.info("DB_USER " + prop.getProperty("DB_USER"));

			DB_USER = prop.getProperty("DB_USER");

			LOG.info("DB_PASSWORD " + prop.getProperty("DB_PASSWORD"));
			DB_PASSWORD = prop.getProperty("DB_PASSWORD");
			LOG.info("DB_CLASS " + prop.getProperty("DB_CLASS"));
			DB_CLASS = prop.getProperty("DB_CLASS");
			LOG.info("DB_URL " + prop.getProperty("DB_URL"));
			DB_URL = prop.getProperty("DB_URL");
			
			ds = new BasicDataSource();
			
			
			ds.setDriverClassName(DB_CLASS);
			ds.setUsername(DB_USER);
			ds.setPassword(DB_PASSWORD);
			ds.setUrl(DB_URL);

			
			ds.setMinIdle(5);
			ds.setMaxIdle(20);
			ds.setMaxOpenPreparedStatements(180);

			
			
			

		} catch (IOException ex) {

			LOG.fatal("Config  File Not Found");

		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOG.error("cannot close inputstream");

				}
			}
		}

	}
	public static Connection getConnection(){
		
		try {
			 con=ds.getConnection();
		} catch (SQLException e) {
			LOG.error("Cannot get the Connection");
		}
		return con;
	}
	public static void closeConnection(){
		
		if(con!=null){
			
			try {
				con.close();
			} catch (SQLException e) {
				LOG.error("Cannot close Connection");
			}
		}
		
	}
	

}
