package fr.diginamic.jdbc.entites;

public class Fournisseur {
	private int id; /** ID */
	private String nom; /** Nom du fournisseur */
	
	/**
	 * Constructeur
	 * @param id
	 * @param nom
	 */
	public Fournisseur(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	/**
	 * Getters et setters
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", nom=" + nom + "]";
	}
	
	@Override
	public boolean equals(Object o){
		return o instanceof Fournisseur && ((Fournisseur)o).getId() == this.getId(); 
		
	}
	
}
