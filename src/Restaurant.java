import java.util.ArrayList;
import java.util.List;

public class Restaurant {
	List<Table> tableRepertoire; 
	List<Employe> employesDuJour;
	menu carteRepas;
	stock stock;
	Transaction transaction;
	Boolean restaurant_ouvert = false;
	
	public Restaurant() {
		this.tableRepertoire= new ArrayList<Table>();
		this.employesDuJour= new ArrayList<Employe>();
		this.carteRepas=new menu();
	}
	public void ouvrirResto() {
		int compteurServeur = 0;
		int compteurBarman = 0;
		int compteurCuisinier = 0;
		for(int i = 0;i<this.employesDuJour.size();i++) {
			if(this.employesDuJour.get(i).getRole() == "Cuisinier") {
				compteurCuisinier++;
			}
			else if(this.employesDuJour.get(i).getRole() == "Barman") {
				compteurBarman++;
			}
			else {
				compteurServeur++;
			}
		}
		if((compteurCuisinier >= 4) && (compteurBarman >= 1) && (compteurServeur >= 2)) {
			this.restaurant_ouvert = true;
		}else {
			System.out.println("Il n'y a pas assez d'employés pour ouvrir le restaurant");
			this.restaurant_ouvert = false;
		}
	}
	
	public void fermerResto() {
		if(this.restaurant_ouvert) {
			this.restaurant_ouvert = false;
		}else {
			System.out.println("Le restaurant n'est pas ouvert");
		}
	}
}
