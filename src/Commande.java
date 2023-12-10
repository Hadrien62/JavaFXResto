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

    public void envoyerCommande() {
        this.Calcule_Addition();
        Transaction transaction = new Transaction(this.num_table, this.addition);
        //menu Menu=new menu();
        //Menu.actualiser_menu(tmp_stock);
        Facture Facturetable= new Facture(this.num_table, this.addition);
        Facturetable.Creation_Facture(this.getPlats(),this.getBoissons());
        String cheminFichier = "commande.txt";
        String donnees = "";
        // Les données à écrire dans le fichier
        donnees += this.num_table + "\n";
        for (Plats plat : this.Plats) {
            donnees += plat.getNum_produit() + ":" + false + ":" + plat.getId();
        }
        for (Boisson boisson : this.Boissons) {
            donnees += boisson.getNum_produit() + ":" + false + ":" + boisson.getId();
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
}
