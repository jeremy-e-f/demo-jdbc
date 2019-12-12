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
import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

public class ArticleDaoJdbc implements ArticleDao{
	
	private static final Logger LOG = LoggerFactory.getLogger("");

	@Override
	public List<Article> extraire() {
		Connection maConnexion= ConnectionJDBC.getInstance();
		ArrayList<Article> listeArticles= new ArrayList<Article>();
		Statement monStatement= null;
		ResultSet curseur= null;
		
		try {
			monStatement = maConnexion.createStatement();
			curseur= monStatement.executeQuery("SELECT * FROM article a JOIN fournisseur f ON(a.id_fourn= f.id);");
			while(curseur.next()){
				listeArticles.add(new Article(curseur.getInt("a.id"), curseur.getString("ref"), curseur.getString("designation"), curseur.getFloat("prix"), new Fournisseur(curseur.getInt("f.id"), curseur.getString("nom"))));
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

		return listeArticles;
	}
	
	@Override
	public float calculMoyenne() {
		Connection maConnexion= ConnectionJDBC.getInstance();
		Statement monStatement= null;
		ResultSet curseur= null;
		Float moyenne= 0F;
		
		try {
			monStatement = maConnexion.createStatement();
			curseur= monStatement.executeQuery("SELECT AVG(prix) as moy FROM article;");
			if(curseur.next()){
				moyenne= curseur.getFloat("moy");
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
		return moyenne;
	}

	@Override
	public void insert(Article article) {
		Connection maConnexion= ConnectionJDBC.getInstance();
		Statement monStatement = null;
		try {
			if(article== null){
				throw new SQLException("Valeur nulle!");
			}
			
			monStatement = maConnexion.createStatement();
			monStatement.executeUpdate("INSERT INTO article(id,ref,designation,prix,id_fourn) VALUES("+article.getId()+
					",'"+article.getRef().replaceAll("'", "''")+
					"','"+article.getDesignation().replaceAll("'", "''")+
					"',"+article.getPrix()+
					","+article.getFournisseur().getId()+");");
			
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			try {
				maConnexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				LOG.error(e1.getMessage());
			}
		} finally {
			try {
				monStatement.close();
				maConnexion.commit();
				maConnexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
			}
		}
	}

	@Override
	public int update(Article article) {
		int nbLigne= 0;
		Connection maConnexion= ConnectionJDBC.getInstance();
		Statement monStatement = null;
		try {
			if(article== null){
				throw new SQLException("Valeur nulle!");
			}
			
			monStatement = maConnexion.createStatement();
			nbLigne= monStatement.executeUpdate("UPDATE article SET ref = '"+article.getRef().replaceAll("'", "''")+
					"', designation = '"+article.getDesignation().replaceAll("'", "''")+
					"', prix = "+article.getPrix()+
					", id_fourn = "+article.getFournisseur().getId()+
					" WHERE id = "+article.getId()+" ;");
			
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			try {
				maConnexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				LOG.error(e1.getMessage());
			}
		} finally {
			try {
				monStatement.close();
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
	public boolean delete(Article article) {
		int nbLigne= 0;
		Connection maConnexion= ConnectionJDBC.getInstance();
		Statement monStatement = null;
		try {
			if(article== null){
				throw new SQLException("Valeur nulle!");
			}
			
			monStatement = maConnexion.createStatement();
			nbLigne= monStatement.executeUpdate("DELETE FROM article WHERE id = "+article.getId()+";");
			
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			try {
				maConnexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				LOG.error(e1.getMessage());
			}
		} finally {
			try {
				monStatement.close();
				maConnexion.commit();
				maConnexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
			}
		}
		return nbLigne == 0;
	}
	
}
