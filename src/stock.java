import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

public class stock {
	private Map<Integer, Integer> Stock_Ingredients; 

	public stock() {
		Map<Integer, Integer> Stock_Ingredients = new HashMap<>();
		this.Stock_Ingredients = Stock_Ingredients;
		lire_Map_Fichier("stock.txt");
	}
	public Map<Integer, Integer> get_Stock_Ingredients() {
		return Stock_Ingredients;
	}
	public Boolean check_ingredient(int numero_ingredient, int quantite_demande) {
			int quantite_Actuelle = Stock_Ingredients.get(numero_ingredient);
			if(quantite_Actuelle >= quantite_demande) {
				return(true);
			}
			else {
				return(false);
			}
		}
	public void setStock_Stock_Ingredients(Map<Integer, Integer> Stock_Ingredients) {
		this.Stock_Ingredients = Stock_Ingredients;
	}
	
	public int[] get_lst_quantities() {
		int[] result = Stock_Ingredients.values().stream().mapToInt(Integer::intValue).toArray();
		return result;
	}
	
	public void add_quantities(int numero_ingredient, int quantite_ajoute) {
        if (Stock_Ingredients.containsKey(numero_ingredient)) {
            int quantite_Actuelle = Stock_Ingredients.get(numero_ingredient);
            Stock_Ingredients.put(numero_ingredient, quantite_Actuelle + quantite_ajoute);
            ecrire_Map_Fichier("stock.txt");
        } else {
            System.out.println("ingredient n'existe pas");
        }
    }

	public void remove_quantities(int numero_ingredient, int quantite_retire) {
        if (Stock_Ingredients.containsKey(numero_ingredient)) {
            int quantite_actuelle = Stock_Ingredients.get(numero_ingredient);
            Stock_Ingredients.put(numero_ingredient, quantite_actuelle - quantite_retire);
            ecrire_Map_Fichier("stock.txt");
        } else {
            System.out.println("ingredient n'existe pas");
        }
    }
	
	public void ecrire_Map_Fichier(String nomFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            for (Map.Entry<Integer, Integer> entry : Stock_Ingredients.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public void lire_Map_Fichier(String nomFichier) {
		try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
			String ligne;
	        	while ((ligne = reader.readLine()) != null) {
	                String[] parts = ligne.split(":");
	                if (parts.length == 2) {
	                    int numeroIngredient = Integer.parseInt(parts[0]);
	                    int quantite = Integer.parseInt(parts[1]);
	                    Stock_Ingredients.put(numeroIngredient, quantite);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}