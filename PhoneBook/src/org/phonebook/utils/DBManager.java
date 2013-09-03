package org.phonebook.utils;

import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;

import org.phonebook.constants.ConstantsError;
import org.phonebook.exceptions.ServiceException;

public class DBManager {
	private static DataSource dataSource = null;
	
	private DBManager() {
		
	}
	
	static {
		try {
			Context envCtx = (Context) new InitialContext().lookup("");
			dataSource = (DataSource) envCtx.lookup("jdbc/PhoneBook");
		} catch (NamingException e) {
			System.err.println(ConstantsError.DATA_SOURCE + ": " + e);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	
	public static Connection getConnection2() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Connection getConnection() throws ServiceException {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (Exception e) {
			throw new ServiceException(ConstantsError.CONNECTION , e);
		}
		return connection;
	}
	
	public static void closeConnection(Connection connection) throws ServiceException {
		try {
			if(connection != null) {
				connection.setAutoCommit(true);
				connection.close();
			}
		} catch (SQLException e) {
			throw new ServiceException(ConstantsError.CONNECTION, e);
		}	
	}
	
	public static void printSQLException(SQLException e) {
		System.err.println("SQLState: " + e.getSQLState());
		System.err.println("Error Code: " + e.getErrorCode());
		System.err.println("Message: " + e.getMessage());
		Throwable t = e.getCause();
		while (t != null) {
			System.out.println("Cause: " + t);
			t = t.getCause();
		}
	}
	
}
