package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String databaseName="mydatabase";
			String URL="jdbc:mysql://localhost:3306/";
			Connection con=DriverManager.getConnection(URL+databaseName,"root","");
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
