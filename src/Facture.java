import java.io.*;
public class Facture {
	private int numeroTable;
	private double prix;
	
	public Facture(int numero, double prix) {
		this.setNumeroTable(numero);
		this.setPrix(prix);
	}

	public int getNumeroTable() {
		return numeroTable;
	}

	public void setNumeroTable(int numeroTable) {
		this.numeroTable = numeroTable;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public void ajouterLigne(Produit produit) {
		String nomFichier = "Facture.txt";
        try {
            File fichier = new File(nomFichier);
            // Vérifie si le fichier n'existe pas
            if (!fichier.exists()) {
                // Crée le fichier s'il n'existe pas
                fichier.createNewFile();
            }
            // Utilise un BufferedWriter pour écrire dans le fichier
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier, true))) {
                // Écriture de la ligne dans le fichier
                writer.write(produit.toString()); // Assurez-vous que votre classe Produit a une méthode toString appropriée
                // Ajout d'un saut de ligne pour la prochaine ligne (si nécessaire)
                writer.newLine();
                this.prix += produit.getPrix();
                System.out.println("Ligne ajoutée avec succès.");
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
	}
	
	public void retirerLigne(Produit produit) {
		String nomFichier = "Facture.txt";
        String produitString = produit.toString(); // Assurez-vous que votre classe Produit a une méthode toString appropriée
        try {
            File fichier = new File(nomFichier);
            File fichierTemp = new File("temp.txt");
            // Vérifie si le fichier existe
            if (!fichier.exists()) {
                System.out.println("Le fichier n'existe pas.");
                return;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(fichier));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(fichierTemp))) {
                String ligneActuelle;
                // Parcours du fichier
                while ((ligneActuelle = reader.readLine()) != null) {
                    // Si la ligne ne correspond pas au produit à retirer, l'écrire dans le fichier temporaire
                    if (!ligneActuelle.equals(produitString)) {
                        writer.write(ligneActuelle);
                        writer.newLine();
                    }
                }
            }
            // Supprimer l'ancien fichier
            fichier.delete();
            // Renommer le fichier temporaire pour prendre la place de l'ancien fichier
            fichierTemp.renameTo(fichier);
            this.prix -= produit.getPrix();
            System.out.println("Ligne retirée avec succès.");
        } catch (IOException e) {
            System.err.println("Erreur lors de la manipulation du fichier : " + e.getMessage());
        }
    }
}
