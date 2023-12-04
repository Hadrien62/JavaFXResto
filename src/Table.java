import java.util.List;

public class Table {
	int num_table;
	Commande commande;
	double addition;
	
	public Table(int numero_table) {
		this.num_table=numero_table;
		this.addition=0.00;
	}
	
	public int Get_num_table(){
		return this.num_table;
	}
	
	public Commande Get_commande(){
		return this.commande;
	}
	
	public double Get_addition(){
		return this.addition;
	}
	
	public void Set_addition(int new_addition){
		this.addition= new_addition;
	}
	
	public void Set_commande(Commande new_commande){
		this.commande= new_commande;
	}
	
	public void Set_num_table(int new_num_table){
		this.num_table= new_num_table;
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