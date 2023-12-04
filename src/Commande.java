
import java.util.List;
import java.util.ArrayList;
import java.io.*;
public class Commande extends Table{
    List<Plats> Plats;
    List<Boisson> Boissons;
    
    public Commande(int num_table,double addition){
		super(num_table);
		this.Boissons=new ArrayList<Boisson>();
		this.Plats=new ArrayList<Plats>();
		this.addition=addition;
		
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
    	 String cheminFichier = "commande.txt";
    	 String donnees = "Plats:\n";
         // Les données à écrire dans le fichier
         for(int i = 0; i<this.Plats.size();i++) {
        	 donnees += this.Plats.get(i).getNom() + "\n";
         }
         donnees += "Boissons:\n";
         for(int i = 0; i<this.Boissons.size(); i++) {
        	 donnees += this.Boissons.get(i).getNom() + "\n";
         }
         try {
             // Crée un objet FileWriter avec le chemin du fichier
             FileWriter fileWriter = new FileWriter(cheminFichier);
             // Crée un objet BufferedWriter pour écrire dans le fichier
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             // Écrire les données dans le fichier
             bufferedWriter.write(donnees);
             bufferedWriter.close();
             System.out.println("Les données ont été écrites avec succès dans le fichier.");
         } catch (IOException e) {
             System.err.println("Une erreur s'est produite lors de l'écriture dans le fichier : " + e.getMessage());
         }
    }
    public void Calcule_Addition() {
		int addition_final=0;
		List<Plats>Plats_commande = this.commande.Plats;
		List<Boisson>Boissons_commande = this.commande.Boissons;
		if(Boissons_commande.size()!=0) {
			for(int i=0;i<Boissons_commande.size();i++) {
				addition_final+=Boissons_commande.get(i).getPrix();
			}
		}
		if(Plats_commande.size()!=0) {
			for(int i=0;i<Plats_commande.size();i++) {
				addition_final+=Plats_commande.get(i).getPrix();
			}
		}
		this.Set_addition(addition_final);
	}
    
}
