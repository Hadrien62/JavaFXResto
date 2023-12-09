
import java.util.HashMap;
import java.util.Map;

/*
1:salades avec tomates
2:salades sans tomates
3:potages oignon
4:potages tomates
5:potages champignon
6:burger tomate,salades
7:burger salade
8:burger que viande
9:pizza tomate fromage
10:pizza champignon
11:pizza saucisse
 */

public class Plats extends Produit {
	
	private Map<Integer, Integer> ingredients;
	
	
	public Plats(int num_plat) {
	
		this.ingredients  = new HashMap<>();
		switch (num_plat) {
		case 1:
			ingredients.put(1, 1);
			ingredients.put(7, 1);
			setPrix(9);
			setNom("salades avec tomates");
			setTemps_prep(10.0);
			num_plat = 1;
			break;
		case 2:
			ingredients.put(7, 1);
			setPrix(9);
			setNom("salades sans tomates");
			setTemps_prep(10.0);
			num_plat = 2;
			break;
		case 3:
			ingredients.put(5, 3);
			setPrix(8);
			setNom("potages oignon");
			setTemps_prep(12.0);
			num_plat = 3;
			break;
		case 4:
			ingredients.put(1, 3);
			setPrix(8);
			setNom("potages tomates");
			setTemps_prep(12.0);
			num_plat = 3;
			break;
		case 5:
			ingredients.put(6, 3);
			setPrix(8);
			setNom("potages champignon");
			setTemps_prep(12.0);
			num_plat = 4;
			break;
		case 6:
			ingredients.put(3, 1);
			ingredients.put(7, 1);
			ingredients.put(1, 1);
			ingredients.put(2, 1);
			setPrix(15);
			setNom("burger tomate, salades");
			setTemps_prep(16.0);
			num_plat = 6;
			break;
		case 7:
			ingredients.put(3, 1);
			ingredients.put(7, 1);
			ingredients.put(2, 1);
			setPrix(15);
			setNom("burger salade");
			setTemps_prep(16.0);
			num_plat = 7;
			break;
		case 8:
			ingredients.put(3, 1);
			ingredients.put(2, 1);
			setPrix(15);
			setNom("burger que viande");
			setTemps_prep(14.0);
			num_plat = 8;
			break;
		case 9:
			ingredients.put(9, 1);
			ingredients.put(1, 1);
			ingredients.put(4, 1);
			setPrix(12);
			setNom("pizza tomate fromage");

			num_plat = 9;
			break;
		case 10:
			ingredients.put(9, 1);
			ingredients.put(1, 1);
			ingredients.put(4,1);
			ingredients.put(6, 1);
			setPrix(12);
			setNom("pizza champignon");
			setTemps_prep(21.0);
			num_plat = 10;
			break;
		case 11:
			ingredients.put(9, 1);
			ingredients.put(1, 1);
			ingredients.put(4, 1);
			ingredients.put(8, 1);
			setPrix(12);
			setNom("pizza saucisse");
			setTemps_prep(22.0);
			num_plat = 11;
			break;
		default:
			System.out.println("Plat non reconnu");
	}
	}

	public Map<Integer, Integer> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Map<Integer,Integer> ingredients) {
		this.ingredients = ingredients;
	}

	public void removestock(stock tmp_stock, ListeCourse tmp_liste) {

		for(int i=1; i<=13 ;i++) {
			if (this.ingredients.containsKey(i)) {
				int quantite_actuelle = ingredients.get(i);
				tmp_stock.remove_quantities(i, quantite_actuelle);
				tmp_liste.add_quantities_automatique(i, quantite_actuelle);
			}
		}

	}
		
	
	public Boolean check_stock(stock tmp_stock) {

		
		for(int i=1; i<=13 ;i++) {
			if (this.ingredients.containsKey(i)) {
				int quantite_actuelle = ingredients.get(i);
		    
				Boolean result = tmp_stock.check_ingredient(i,quantite_actuelle);
				if(result == false) {
					return false;
				}
			}
		}
		return true;

	}
}
	


