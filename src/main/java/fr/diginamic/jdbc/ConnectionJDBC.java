package fr.diginamic.jdbc;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionJDBC {
	/* Nom du fichier .properties, contenant les paramètres dans src/main/ressources */
	private final static String NOMFICHIERCONFIG= "database";

	/* Pool de connexion */
	private final static ComboPooledDataSource CPDS;
	
	static{
		/* On récupère les paramètres de connexion dans le fichier config */
		ResourceBundle monFichierConf = ResourceBundle.getBundle(NOMFICHIERCONFIG);
		String driverName = monFichierConf.getString("database.driver");
		String url = monFichierConf.getString("database.url");
		String user = monFichierConf.getString("database.user");
		String password = monFichierConf.getString("database.password");
		
		
		/* Création d'un Pool de connexion */
		CPDS= new ComboPooledDataSource();
		try {
			CPDS.setDriverClass(driverName);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
			System.exit(1);
		}
		CPDS.setJdbcUrl(url);
		CPDS.setUser(user);
		CPDS.setPassword(password);
	}
	
	public static Connection getInstance(){
		/* On récupère une instance de connection */
		try {
			return CPDS.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new RuntimeException("Impossible de récupérer une nouvelle connexion à la base de données");
		}
	}
	
}
