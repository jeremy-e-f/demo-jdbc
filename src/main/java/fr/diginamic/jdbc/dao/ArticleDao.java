package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entites.Article;

public interface ArticleDao {
	/**
	 * Extrait la liste de tous les articles
	 * @return List<Article>
	 */
	List<Article> extraire();
	
	/**
	 * Insère un nouvel article
	 * @param article
	 */
	void insert(Article article);
	
	/**
	 * Met à jour l'article passé en paramètre
	 * @param article
	 * @return
	 */
	int update(Article article);
	
	/**
	 * Supprime l'article passé en paramètre
	 * @param article
	 * @return
	 */
	boolean delete(Article article);
	
	/**
	 * Renvoie la moyenne de tous les articles
	 * @return
	 */
	float calculMoyenne();
	
}

