package com.epam.finalweb.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import com.epam.finalweb.dao.exception.PoolException;

public class ConnectionPool {

	
	private static BlockingQueue<Connection> availableConnection;
	private static BlockingQueue<Connection> busyConnection;
	private static final int maxPoolSize = 3;
	private static final Logger LOG = Logger.getLogger(ConnectionPool.class);

	private ConnectionPool() {

	}
	private static class ConnectionPoolHolder{
		private static final ConnectionPool INSTANCE = new ConnectionPool();
		
	}

	public static ConnectionPool getInstance() {
		return ConnectionPoolHolder.INSTANCE;

	}

	public void init() throws PoolException {

		Connection con = null;

		URI jdbUri = null;

		try {
			jdbUri = new URI(ConfigParam.getDB_URI());
			String userName = ConfigParam.getDB_USER();
			String password = jdbUri.getUserInfo().split(":")[1];
			String port = String.valueOf(jdbUri.getPort());
			String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();
			Class.forName(ConfigParam.getDB_CLASS());
			int connectionNo = 0;
			availableConnection = new ArrayBlockingQueue<Connection>(maxPoolSize, true);
			for (; connectionNo < maxPoolSize; connectionNo++) {

				con = createConnection(jdbUrl, userName, password);
				availableConnection.put(con);

			}

			busyConnection = new ArrayBlockingQueue<Connection>(maxPoolSize, true);

		} catch (URISyntaxException e) {
			throw new PoolException("URi syntax Error", e);

		} catch (InterruptedException e) {
			throw new PoolException("Cannot add Connection to pool", e);

		} catch (ClassNotFoundException e) {
			throw new PoolException("Driver Class Not found", e);
		}

	}

	public Connection getConnection() throws PoolException {
		Connection con = null;

		try {
			con = availableConnection.take();

			busyConnection.put(con);

		} catch (InterruptedException e) {
			throw new PoolException("Thread Interrupted", e);
		}

		return con;

	}

	public void returnConnection(Connection connection) throws PoolException {

		busyConnection.remove(connection);
		try {
			availableConnection.put(connection);
		} catch (InterruptedException e) {
			throw new PoolException("Thread Interrupted ", e);
		}

	}

	public void destroy() {
		for (Connection connection : availableConnection) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.error("Cannot Close Connection", e);
			}
		}
		for (Connection connection : busyConnection) {
				try {
				connection.close();
			} catch (SQLException e) {
				LOG.error("Cannot Close Connection", e);
			}
		}

	}

	private Connection createConnection(String jdbUrl, String userName, String password) throws PoolException {
		Connection con;
		try {
			con = DriverManager.getConnection(jdbUrl, userName, password);
		} catch (SQLException e) {
			throw new PoolException("Cannot create Connection", e);
		}
		return con;

	}
}
