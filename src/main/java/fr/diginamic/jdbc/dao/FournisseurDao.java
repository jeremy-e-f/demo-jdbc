package fr.diginamic.jdbc.dao;

import java.util.List;
import fr.diginamic.jdbc.entites.Fournisseur;

public interface FournisseurDao {
	/**
	 * Extrait la liste de tous les fournisseurs
	 * @return List<Article>
	 */
	List<Fournisseur> extraire();
	
	/**
	 * Insère un nouvel fournisseur
	 * @param fournisseur
	 */
	void insert(Fournisseur fournisseur);
	
	/**
	 * Met à jour le fournisseur passé en paramètre
	 * @param fournisseur
	 * @return
	 */
	int update(String ancienNom, String nouveauNom);
	
	/**
	 * Supprime le fournisseur passé en paramètre
	 * @param article
	 * @return
	 */
	boolean delete(Fournisseur fournisseur);

}
