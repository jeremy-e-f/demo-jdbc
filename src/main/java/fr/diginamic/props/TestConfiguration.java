package fr.diginamic.props;

import java.util.ResourceBundle;

public class TestConfiguration {

	public static void main(String[] args) {
		ResourceBundle monFichierConf = ResourceBundle.getBundle("config");
		String driverName = monFichierConf.getString("database.driver");
		System.out.println(driverName);
		
		String urlAdress = monFichierConf.getString("database.url");
		System.out.println(urlAdress);
		
		String userName = monFichierConf.getString("database.user");
		System.out.println(userName);
		
		String password = monFichierConf.getString("database.password");
		System.out.println(password);
	}

}
