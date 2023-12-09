import java.util.List;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Facture {

    int num_table;
    double prix;

    public Facture(int num_table, double prix) {
        this.num_table = num_table;
        this.prix = prix;
    }

    public void Creation_Facture(List<Plats> Plats, List<Boisson> Boissons) {
        String cheminFichier = "Facture.txt";


        // Obtenez la date et l'heure actuelles
        LocalDateTime maintenant = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dateHeure = maintenant.format(formatter);

        String donnees = "Facture de la table :" + this.num_table + "\n";
        donnees += "Date et heure : " + dateHeure + "\n";
        if (!Plats.isEmpty()) {
            donnees += "Plats:\n";
            for (Plats plat : Plats) {
                donnees += plat.getNom() + "   " + plat.getPrix() + " €\n";
            }
        }
        if (!Boissons.isEmpty()) {
            donnees += "Boissons:\n";
            for (Boisson boisson : Boissons) {
                donnees += boisson.getNom() + "   " + boisson.getPrix() + "€\n";
            }
        }

        donnees += "Montant à payer :  " + this.prix + "€";
        donnees += "\nFin de la Facture de la table :  " + this.num_table + "\n\n";

        try {
            // Créez un objet FileWriter avec le chemin du fichier en mode ajout
            FileWriter fileWriter = new FileWriter(cheminFichier, true);

            // Créez un objet BufferedWriter pour écrire dans le fichier
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Écrivez les données dans le fichier
            bufferedWriter.write(donnees);

            // Fermez le BufferedWriter
            bufferedWriter.close();

            System.out.println("Les données ont été ajoutées avec succès dans le fichier.");

        } catch (IOException e) {
            System.err.println("Une erreur s'est produite lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }

}