package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.diginamic.jdbc.ConnectionJDBC;
import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao{

	private static final Logger LOG = LoggerFactory.getLogger("");
	
	@Override
	public List<Fournisseur> extraire() {
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

		return listeFournisseurs;
	}
	
	@Override
	public void insert(Fournisseur fournisseur) {
		Connection maConnexion= ConnectionJDBC.getInstance();
		try {
			if(fournisseur== null){
				throw new SQLException("Valeur nulle!");
			}
			
			Statement monStatement = maConnexion.createStatement();
			monStatement.executeUpdate("INSERT INTO fournisseur(id,nom) VALUES("+fournisseur.getId()+",'"+fournisseur.getNom().replaceAll("'", "''")+"');");
			
		} catch (SQLException e) {
			try {
				maConnexion.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
				LOG.error(e.getMessage());
			}
		} finally {
			try {
				maConnexion.commit();
				maConnexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
			}
		}
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		
		Connection maConnexion= ConnectionJDBC.getInstance();
		int nbLigne= 0;
		try {
			if(nouveauNom== null || ancienNom== null){
				throw new SQLException("Valeur nulle!");
			}
			
			Statement monStatement = maConnexion.createStatement();
			nbLigne= monStatement.executeUpdate("UPDATE fournisseur SET nom = '"+nouveauNom.replaceAll("'", "''")+"' WHERE nom = '"+ancienNom.replaceAll("'", "''")+"';");
			if(nbLigne<= 0){
				throw new SQLException("Aucune ligne mise à jour!");
			}
			
		} catch (SQLException e) {
			try {
				maConnexion.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
				LOG.error(e.getMessage());
			}
		} finally {
			try {
				maConnexion.commit();
				maConnexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
			}
		}
		return nbLigne;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		Connection maConnexion= ConnectionJDBC.getInstance();
		int retour= 0;
		try {
			if(fournisseur== null){
				throw new SQLException("Valeur nulle!");
			}
			
			Statement monStatement = maConnexion.createStatement();
			retour= monStatement.executeUpdate("DELETE FROM fournisseur WHERE id = "+fournisseur.getId()+";");
			
		} catch (SQLException e) {
			try {
				maConnexion.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
				LOG.error(e.getMessage());
			}
		} finally {
			try {
				maConnexion.commit();
				maConnexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
			}
		}
		return retour!=0;
	}

}
