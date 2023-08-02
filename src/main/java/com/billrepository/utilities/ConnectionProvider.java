// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	static Connection con;
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/billrepository";
			String user = "root";
			String passwd = "Kv139@mysql";
			con = DriverManager.getConnection(url,user,passwd);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
}
