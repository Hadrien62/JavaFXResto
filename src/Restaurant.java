import java.util.ArrayList;
import java.util.List;

public class Restaurant {
	private List<Table> tableRepertoire;
	private List<Employe> employesDuJour;
	private menu carteRepas;
	private stock stock;
	private Transaction transaction;
	private Boolean restaurant_ouvert = false;
	
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
			if(this.employesDuJour.get(i).getRole().equals("Cuisinier")) {
				compteurCuisinier++;
			}
			else if(this.employesDuJour.get(i).getRole().equals("Barman")) {
				compteurBarman++;
			}
			else {
				compteurServeur++;
				System.out.println(this.employesDuJour.get(i).getRole());
			}
		}
		if((compteurCuisinier >= 4) && (compteurBarman >= 1) && (compteurServeur >= 2)) {
			this.restaurant_ouvert = true;
		}else {
			System.out.println("Il n'y a pas assez d'employés pour ouvrir le restaurant " + compteurCuisinier + " " + compteurBarman + " " + compteurServeur);
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

	public void setEmployesDuJour(List<Employe> employesDuJour) {
		this.employesDuJour = employesDuJour;
	}

	public List<Employe> getEmployesDuJour() {
		return employesDuJour;
	}

	public Boolean getRestaurant_ouvert() {
		return restaurant_ouvert;
	}

	public menu getCarteRepas() {
		return carteRepas;
	}

	public void setCarteRepas(menu carteRepas) {
		this.carteRepas = carteRepas;
	}

	public List<Table> getTableRepertoire() {
		return tableRepertoire;
	}

	public void setTableRepertoire(List<Table> tableRepertoire) {
		this.tableRepertoire = tableRepertoire;
	}
}
