package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class MihirUtil {

	public static Connection createConnection()
	{
		Connection conn = null;
		
		try {
			// Load the Driver
			Class.forName("com.mysql.jdbc.Driver");
			//Establish the Connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mihir_java_7.30","root","");
			//Write a Query
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
