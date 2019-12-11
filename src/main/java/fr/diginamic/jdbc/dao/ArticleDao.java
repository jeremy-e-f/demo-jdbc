package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entites.Article;

public interface ArticleDao {

	List<Article> extraire();
	
	void insert(Article article);
	
	int update(Article article);
	
	boolean delete(Article article);
	
	float calculMoyenne();
	
}

