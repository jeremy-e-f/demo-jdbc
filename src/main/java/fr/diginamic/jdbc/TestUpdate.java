package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {

	public static void main(String[] args) {
		Connection maConnexion= ConnectionJDBC.getInstance();
		try {
			Statement monStatement = maConnexion.createStatement();
			int nbLigne= monStatement.executeUpdate("UPDATE fournisseur SET nom = 'La Maison des Peintures' WHERE id = 4;");
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
