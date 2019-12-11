package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDao;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestJdbcFournisseur {

	public static void main(String[] args) {
		FournisseurDao dao= new FournisseurDaoJdbc();
		dao.insert(new Fournisseur(4,"L’Espace Création"));
	}

}
