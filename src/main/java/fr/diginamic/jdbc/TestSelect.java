package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.diginamic.jdbc.entites.Fournisseur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSelect {
	
	private static final Logger LOG = LoggerFactory.getLogger("");

	public static void main(String[] args) {
		
		Connection maConnexion= ConnectionJDBC.getInstance();
		ArrayList<Fournisseur> listeFournisseurs= new ArrayList<Fournisseur>();
		Statement monStatement= null;
		ResultSet curseur= null;
		
		try {
			monStatement = maConnexion.createStatement();
			curseur= monStatement.executeQuery("SELECT * FROM fournisseur;");
			while(curseur.next()){
				listeFournisseurs.add(new Fournisseur(curseur.getInt("id"), curseur.getString("nom")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		} finally {
			try {
				curseur.close();
				monStatement.close();
				maConnexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
			}
		}
		
		for(Fournisseur fournisseur : listeFournisseurs){
			System.out.println(fournisseur);
		}

	}

}
