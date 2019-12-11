package fr.diginamic.jdbc;

import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.dao.ArticleDao;
import fr.diginamic.jdbc.dao.ArticleDaoJdbc;
import fr.diginamic.jdbc.dao.FournisseurDao;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestJdbcArticles {

	public static void main(String[] args) {
		ArticleDao daoA= new ArticleDaoJdbc();
		
		/* Ajout d'un nouvel article */
		FournisseurDao daoF= new FournisseurDaoJdbc();
		Fournisseur nouvFourn= new Fournisseur(5, "La Maison de la Peinture");
		daoF.insert(nouvFourn);
		
		/* Insertion des nouveaux articles */
		List<Article> listeArticles= new ArrayList<Article>();
		listeArticles.add(new Article(11, "11", "Peinture blanche 1L", 12.5F, nouvFourn));
		listeArticles.add(new Article(12, "12", "Peinture rouge mate 1L", 15.5F, nouvFourn));
		listeArticles.add(new Article(13, "13", "Peinture noire laquée 1L", 17.8F, nouvFourn));
		listeArticles.add(new Article(14, "14", "Peinture bleue mate 1L", 15.5F, nouvFourn));
		
		for(Article art : listeArticles){
			daoA.insert(art);
		}
		
		/* On augmente leur prix de 25% */
		for(Article art : listeArticles){
			art.setPrix(art.getPrix()*1.25F);
		}
		
		for(Article art : listeArticles){
			daoA.update(art);
		}
		
		/* On affiche la liste de tous les articles */
		listeArticles= daoA.extraire();
		for(Article art : listeArticles){
			System.out.println(art);
		}
		
		/* Calcul de la moyenne des articles */
		System.out.println("La moyenne de tous les articles est de: "+daoA.calculMoyenne());
		
		/* Suppression des articles */
		for(Article art : listeArticles){
			if(art.getDesignation().contains("Peinture")){
				daoA.delete(art);
			}
		}
		daoF.delete(nouvFourn);
		
	}

}
