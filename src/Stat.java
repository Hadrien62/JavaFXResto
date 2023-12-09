import java.io.*;

public class Stat {
    private int chiffre_d_affaire;
    private int nb_client;
    private int nb_plat_vendu;
    private int nb_cocktail_vendu;

    public Stat() {
        lireDepuisFichier("stats.txt");
    }

    public int getChiffre_d_affaire() {
        return chiffre_d_affaire;
    }

    public void setChiffre_d_affaire(int chiffre_d_affaire) {
        this.chiffre_d_affaire = chiffre_d_affaire;
    }

    public int getNb_client() {
        return nb_client;
    }

    public void setNb_client(int nb_client) {
        this.nb_client = nb_client;
    }

    public int getNb_plat_vendu() {
        return nb_plat_vendu;
    }

    public void setNb_plat_vendu(int nb_plat_vendu) {
        this.nb_plat_vendu = nb_plat_vendu;
    }

    public int getNb_cocktail_vendu() {
        return nb_cocktail_vendu;
    }

    public void setNb_cocktail_vendu(int nb_cocktail_vendu) {
        this.nb_cocktail_vendu = nb_cocktail_vendu;
    }

    // Méthode pour écrire les statistiques dans un fichier
    public void ecrireDansFichier(String cheminFichier) {
        try (BufferedWriter ecrivain = new BufferedWriter(new FileWriter(cheminFichier))) {
            ecrivain.write("Chiffre d'affaire : " + chiffre_d_affaire);
            ecrivain.newLine();
            ecrivain.write("Nombre de clients : " + nb_client);
            ecrivain.newLine();
            ecrivain.write("Nombre de plats vendus : " + nb_plat_vendu);
            ecrivain.newLine();
            ecrivain.write("Nombre de cocktails vendus : " + nb_cocktail_vendu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour lire les statistiques depuis un fichier
    public void lireDepuisFichier(String cheminFichier) {
        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            setChiffre_d_affaire(Integer.parseInt(lecteur.readLine().split(": ")[1]));
            setNb_client(Integer.parseInt(lecteur.readLine().split(": ")[1]));
            setNb_plat_vendu(Integer.parseInt(lecteur.readLine().split(": ")[1]));
            setNb_cocktail_vendu(Integer.parseInt(lecteur.readLine().split(": ")[1]));
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void add_stat(int quantite, int type){
        switch (type) {
            case 1:

                this.chiffre_d_affaire += quantite;
            case 2:
                this.nb_client += quantite;
            case 3:
                this.nb_plat_vendu += quantite;
            case 4:
                this.nb_cocktail_vendu += quantite;
        }

        ecrireDansFichier("stats.txt");
    }
}

