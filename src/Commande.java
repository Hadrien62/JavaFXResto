import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
public class Commande extends Table {
    List<Plats> Plats;
    List<Boisson> Boissons;

    public Commande(int num_table){
        super(num_table);
        this.Boissons=new ArrayList<Boisson>();
        this.Plats=new ArrayList<Plats>();
        this.addition=0;

    }
    public void add_Plats(Plats Plat){
        this.Plats.add(Plat);
    }
    public void add_Boissons(Boisson Drink){
        this.Boissons.add(Drink);
    }
    public List<Plats> getPlats(){
        return this.Plats;
    }

    public List<Boisson> getBoissons(){
        return this.Boissons;
    }

    public void setBoissons(List<Boisson> boissons){
        this.Boissons = boissons;
    }

    public void setPlatst(List<Plats> plats) {
        this.Plats = plats;
    }

    public void envoyerCommande(ListeCourse tmp_list) {
        this.Calcule_Addition();
        Transaction transaction = new Transaction(this.num_table, this.addition);
        Facture Facturetable= new Facture(this.num_table, this.addition);
        Facturetable.Creation_Facture(this.getPlats(),this.getBoissons());
        String cheminFichier = "commande.txt";
        String donnees = "";
        stock tmp_stock = new stock();
        // Les données à écrire dans le fichier
        donnees += this.num_table + "\n";
        for (Plats plat : this.Plats) {
            donnees += plat.getNum_produit() + ":" + false + ":" + plat.getId() + "\n";
            plat.removestock(tmp_stock,tmp_list);
        }
        for (Boisson boisson : this.Boissons) {
            donnees += boisson.getNum_produit() + ":" + false + ":" + boisson.getId()+ "\n";
            boisson.removestock(tmp_stock,tmp_list);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) { // Ecriture du DATA
            // Écrire les données dans le fichier
            writer.write(donnees);
            writer.newLine();
            System.out.println("Les données ont été écrites avec succès dans le fichier.");
        } catch (IOException e) {
            System.err.println("Une erreur s'est produite lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }
    public void Calcule_Addition() {
        int addition_final = 0;
        List<Plats> Plats_commande = this.Plats;
        List<Boisson> Boissons_commande = this.Boissons;
        if (!Boissons_commande.isEmpty()) {
            for (Boisson boisson : Boissons_commande) {
                addition_final += boisson.getPrix();
            }
        }
        if (!Plats_commande.isEmpty()) {
            for (Plats plats : Plats_commande) {
                addition_final += plats.getPrix();
            }
        }
        this.Set_addition(addition_final);
    }

    public void Get_addition_From_txt(){
        List<Plats>listeCommandePlats = new ArrayList<>();
        List<Boisson>listeCommandeBoisson = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("commande.txt"))) {
            String numTable;
            while ((numTable = reader.readLine()) != null) {
                int tableNumber = Integer.parseInt(numTable);
                this.num_table = tableNumber;
                String platInfo;
                while ((platInfo = reader.readLine()) != null && platInfo.length() > 0) {
                    String[] parts = platInfo.split(":");
                    if (parts.length == 3) {
                        int identifiantPlat = Integer.parseInt(parts[0]);

                        if (identifiantPlat <= 11) {
                            Plats plat = new Plats(identifiantPlat);
                            plat.setNumTable(tableNumber);
                            listeCommandePlats.add(plat);
                        }
                        if (identifiantPlat > 11) {
                            Boisson boisson = new Boisson(identifiantPlat);
                            boisson.setNumTable(tableNumber);
                            listeCommandeBoisson.add(boisson);
                        }
                    }
                }
            }
            if (!listeCommandeBoisson.isEmpty()){
                for (int i = 0; i < listeCommandeBoisson.size(); i++) {
                    this.addition+=listeCommandeBoisson.get(i).getPrix();
                }
            }
            if (!listeCommandePlats.isEmpty()) {
                for (int i = 0; i < listeCommandePlats.size(); i++) {
                    this.addition += listeCommandePlats.get(i).getPrix();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Impossible de charger les données des employés.");
        }
    }

    public void clearCommandeFile() {
        try {
            File file = new File("commande.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Écrire une chaîne vide dans le fichier
            bufferedWriter.write("");

            // Fermer le BufferedWriter
            bufferedWriter.close();

            System.out.println("Le fichier commande.txt a été vidé avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
