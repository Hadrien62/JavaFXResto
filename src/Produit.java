

public abstract class Produit {
	
	private String nom;
	private int prix;
	private Double temps_prep;

	private int num_produit;
	private boolean pret;

	private int numTable;
	
	public Produit() {
		this.nom = "";
		this.temps_prep = 0.0;
		this.prix = 0;
		this.num_produit = 20;
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


	public int getNum_produit() {
		return num_produit;
	}

	public void setNum_produit(int num_produit) {
		this.num_produit = num_produit;
	}

	public boolean isPret() {
		return pret;
	}

	public void setPret(boolean pret) {
		this.pret = pret;
	}

	public int getNumTable() {
		return numTable;
	}

	public void setNumTable(int numTable) {
		this.numTable = numTable;
	}
}
