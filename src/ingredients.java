

public class ingredients {
	private String nom;
	private Double prix;
	private int quantite;
	
	public ingredients(String nom, Double prix ,int quantite) {
		this.nom =nom;
		this.prix =prix;
		this.quantite = quantite;
	}
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
}
