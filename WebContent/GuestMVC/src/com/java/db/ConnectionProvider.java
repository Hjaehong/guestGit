package com.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static Connection conn = null;
	
	public static Connection getConnection() { // Connection conn= ConnectinoProvider.getConnection();
		
		try {
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String id="sys as sysdba";
			String pass ="1234";
			
			conn = DriverManager.getConnection(url,id,pass);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return conn;
	}
}
