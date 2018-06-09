package com.example.pitangua.psystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.pitangua.psystem.exception.UnhandledException;

public class ConnectionManager {

	private String url = "jdbc:mysql://localhost/psystem_db";
	private String user = "root";
	private String pass = "root";

	private Connection connection = null;

	private static ConnectionManager instance = null;

	private ConnectionManager() {
	}

	public static synchronized Connection getConnection() {
		if (instance == null) {
			instance = new ConnectionManager();
		}

		try {
			if (instance.connection == null || instance.connection.isClosed()) {
				instance.connection = DriverManager.getConnection(instance.url, instance.user, instance.pass);
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Connection Error", e);
		}

		return instance.connection;
	}

	public static synchronized void closeConnection() throws SQLException {
		getConnection().close();
	}

}
