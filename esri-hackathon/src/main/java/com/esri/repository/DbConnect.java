package com.esri.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {

	private static final String USERNAME = "esri";
	private static final String PASSWORD = "password";
	private static final String M_CONN_STRING = "jdbc:mysql://localhost:3306/db_esri";
	// private static final String M_CONN_STRING = "jdbc:mysql://cork-street.cloudapp.net:3306/db_corkstreet";
	// http://cork-street.cloudapp.net/corkstreet-hair-and-beauty/web/v1/services

	public static Connection getConnection(DBType dbType) throws SQLException {
		switch (dbType) {
			case MYSQL:
				// System.out.println("Conn opening");
				return DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
	//		case HSQLDB:
	//			return DriverManager.getConnection(H_CONN_STRING, USERNAME, PASSWORD);
			default:
				return null;
		}
	}
	public static void processException(SQLException e) {
		System.err.println("Error message: " + e.getMessage());
		System.err.println("Error code: " + e.getErrorCode());
		System.err.println("SQL state: " + e.getSQLState());
	}

	
}
