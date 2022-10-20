package com.webgames.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.webgames.model.User;


public class DBConnection {

	
	private static DataSource ds;

	private DBConnection() {
		ds = getDataSource();
	}

	// Provides thread-safe initialization of the Singleton
	private static class Holder {
		private static final DBConnection INSTANCE = new DBConnection();
	}

	// JDNI lookup of Database Connection Pool
	private static DataSource getDataSource() {
		DataSource ds = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/webgames");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		return ds;
	}

	public static DBConnection getInstance() {
		return Holder.INSTANCE;
	}
	
	public static void main(String[] args) {
		System.out.println(DBConnection.getInstance());
	}
	
	public void addUser(User user) {
		String sql = "INSERT INTO webgames.users (user_email, user_first, user_last, user_pass) VALUES (?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getFirstName());
			stmt.setString(3, user.getLastName());
			stmt.setObject(4, user.getPassword());
			stmt.executeUpdate();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
