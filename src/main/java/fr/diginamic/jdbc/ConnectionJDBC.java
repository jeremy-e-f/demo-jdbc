package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionJDBC {

	public static Connection getInstance(){
		//ResourceBundle monFichierConf = ResourceBundle.getBundle("databaseCleverCloud");
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String driverName = monFichierConf.getString("database.driver");
		String url = monFichierConf.getString("database.url");
		String user = monFichierConf.getString("database.user");
		String password = monFichierConf.getString("database.password");
				
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Connection connection= null;
		try {
			connection= DriverManager.getConnection(url,user,password);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
}
