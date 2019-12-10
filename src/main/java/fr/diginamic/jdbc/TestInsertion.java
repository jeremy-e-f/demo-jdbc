package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsertion {

	public static void main(String[] args) {
		Connection maConnexion= ConnectionJDBC.getInstance();
		try {
			Statement monStatement = maConnexion.createStatement();
			int nbLigne= monStatement.executeUpdate("INSERT INTO fournisseur(id,nom) VALUES(4,'La Maison de la Peinture');");
			System.out.println(nbLigne);
			
		} catch (SQLException e) {
			try {
				maConnexion.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
			}
		} finally {
			try {
				maConnexion.commit();
				maConnexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
