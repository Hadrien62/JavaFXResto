import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class Manager extends Employe{
private List<Employe> equipe;
public Manager(String username, String pass, double salaire, int nbSoir, List<Employe> equipe) {
	super(username, pass, "Manager", salaire, nbSoir);
	this.equipe = equipe;
}
public Manager(String username, String pass, double salaire, int nbSoir) {
	super(username, pass, "Manager", salaire, nbSoir);
	this.equipe = new ArrayList<Employe>();
}
public List<Employe> getEquipe(){
	return this.equipe;
}
public void setEquipe(List<Employe> equipe) {
	this.equipe = equipe;
}
public List<Employe> ajouterMembre(Employe membre){
	List<Employe> equipeGerer = this.equipe;
	equipeGerer.add(membre);
	return equipeGerer;
}
public List<Employe> retirerMembre(Employe membre){
	List<Employe> equipeGerer = this.equipe;
	equipeGerer.remove(membre);
	return equipeGerer;
}
public void listeCourse(String produit) {
    // Nom du fichier dans lequel la liste de courses sera enregistrée
    String nomFichier = "listeCourse.txt";

    try {
        File fichier = new File(nomFichier);

        // Vérifie si le fichier n'existe pas
        if (!fichier.exists()) {
            // Crée le fichier s'il n'existe pas
            fichier.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier, true))) {
            // Écriture du produit dans le fichier
            writer.write(produit);
            // Ajout d'un saut de ligne pour le prochain produit (si nécessaire)
            writer.newLine();
            System.out.println("Produit ajouté à la liste de courses avec succès.");
        }
    } catch (IOException e) {
        System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
    }
}

}
