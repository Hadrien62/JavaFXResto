
import java.util.HashMap;
import java.util.Map;

public class Plats extends Produit {
	private Map<Integer, Integer> ingredients;
	public Plats(int num_plat) {
		this.ingredients  = new HashMap<>();
		setNum_produit(num_plat);
		switch (num_plat) {
		case 1:
			ingredients.put(1, 1);
			ingredients.put(7, 1);
			setPrix(9);
			setNom("Salade Tomate");
			setTemps_prep(10.0);

			break;
		case 2:
			ingredients.put(7, 1);
			setPrix(9);
			setNom("Salade");
			setTemps_prep(10.0);
			break;
		case 3:
			ingredients.put(5, 3);
			setPrix(8);
			setNom("Soupe Oignon");
			setTemps_prep(12.0);
			break;
		case 4:
			ingredients.put(1, 3);
			setPrix(8);
			setNom("Soupe Tomate");
			setTemps_prep(12.0);
			break;
		case 5:
			ingredients.put(6, 3);
			setPrix(8);
			setNom("Soupe Enoki");
			setTemps_prep(12.0);
			break;
		case 6:
			ingredients.put(3, 1);
			ingredients.put(7, 1);
			ingredients.put(1, 1);
			ingredients.put(2, 1);
			setPrix(15);
			setNom("Burger");
			setTemps_prep(16.0);
			break;
		case 7:
			ingredients.put(3, 1);
			ingredients.put(7, 1);
			ingredients.put(2, 1);
			setPrix(15);
			setNom("Burger Végétarien");
			setTemps_prep(16.0);
			break;
		case 8:
			ingredients.put(3, 1);
			ingredients.put(2, 1);
			setPrix(15);
			setNom("Burger Carnivore");
			setTemps_prep(14.0);
			break;
		case 9:
			ingredients.put(9, 1);
			ingredients.put(1, 1);
			ingredients.put(4, 1);
			setPrix(12);
			setNom("Pizza 4 fromages");
			break;
		case 10:
			ingredients.put(9, 1);
			ingredients.put(1, 1);
			ingredients.put(4,1);
			ingredients.put(6, 1);
			setPrix(12);
			setNom("Pizza Calzone");
			setTemps_prep(21.0);
			break;
		case 11:
			ingredients.put(9, 1);
			ingredients.put(1, 1);
			ingredients.put(4, 1);
			ingredients.put(8, 1);
			setPrix(12);
			setNom("Pizza Napolitaine");
			setTemps_prep(22.0);
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

				Boolean result = tmp_stock.check_ingredient(i,quantite_actuelle*10);
				if(result == false) {
					return false;
				}
			}
		}
		return true;

	}
}
	


