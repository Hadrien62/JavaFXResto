

public abstract class Produit {
	
	private String nom;
	private int prix;
	private Double temps_prep;
	
	
	public Produit() {
		this.nom = "";
		this.temps_prep = 0.0;
		this.prix = 0;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public Double getTemps_prep() {
		return temps_prep;
	}

	public void setTemps_prep(Double temps_prep) {
		this.temps_prep = temps_prep;
	}




}
