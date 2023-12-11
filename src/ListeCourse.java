import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
public class ListeCourse {
	private Map<Integer, Integer> lst_course ;
	private Map<Integer, Integer> lst_course_automatique;
	public ListeCourse() {
		this.lst_course = new HashMap<>();
		this.lst_course_automatique = new HashMap<>();
	}
	public Map<Integer, Integer> getLst_course() {
		return lst_course;
	}
	public void setLst_course(Map<Integer, Integer> lst_course) {
		this.lst_course = lst_course;
	}
	public void add_quantities(int numero_ingredient) {
		if (lst_course.containsKey(numero_ingredient)) {
			int quantite_Actuelle = lst_course.get(numero_ingredient);
			lst_course.put(numero_ingredient, quantite_Actuelle + 1);
		} else {
			lst_course.put(numero_ingredient,1);
		}
	}

	public void remove_quantities(int numero_ingredient) {
		if (lst_course.containsKey(numero_ingredient)) {
			int quantite_actuelle = lst_course.get(numero_ingredient);
			lst_course.put(numero_ingredient, quantite_actuelle - 1);
		} else {
			System.out.println("ingredient pas dans la liste");
		}
	}

	public int[] get_liste_course_quanitite(){
		int[] result = lst_course.values().stream().mapToInt(Integer::intValue).toArray();
		return result;
	}
	public  String[] get_imagePaths_liste_course() {
		// Conversion du tableau en liste pour permettre l'ajout d'éléments
		List<String> lst_tmp = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : lst_course.entrySet()) {
			int key = entry.getKey();
			switch (key) {
				case 1:
					lst_tmp.add("images/Tomato.png");
				case 2:
					lst_tmp.add("images/Beef.png");
				case 3:
					lst_tmp.add("images/Bread.png");
				case 4:
					lst_tmp.add("images/Cheese.png");
				case 5:
					lst_tmp.add("images/Garlic.png");
				case 6:
					lst_tmp.add("images/Mushroom.png");
				case 7:
					lst_tmp.add("images/Pan.png");
				case 8:
					lst_tmp.add("images/Salad.png");
				case 9:
					lst_tmp.add("images/Sausage.png");
				case 10:
					lst_tmp.add("images/Beef.png");
				case 11:
					lst_tmp.add("images/Beef.png");
				case 12:
					lst_tmp.add("images/Beef.png");
				case 13:
					lst_tmp.add("images/Beef.png");
			}
		}
		// Conversion de la liste en un nouveau tableau
		String[] result  = lst_tmp.toArray(new String[0]);
		return result;
	}
	public  int[] get_prix_liste_course() {
		// Conversion du tableau en liste pour permettre l'ajout d'éléments
		List<Integer> lst_tmp = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : lst_course.entrySet()) {
			int key = entry.getKey();
			switch (key) {
				case 1:
					lst_tmp.add(3);
					break;
				case 2:
					lst_tmp.add(2);
					break;
				case 3:
					lst_tmp.add(4);
					break;
				case 4:
					lst_tmp.add(3);
					break;
				case 5:
					lst_tmp.add(5);
					break;
				case 6:
					lst_tmp.add(2);
					break;
				case 7:
					lst_tmp.add(6);
					break;
				case 8:
					lst_tmp.add(8);
					break;
				case 9:
					lst_tmp.add(1);
					break;
				case 10:
					lst_tmp.add(2);
					break;
				case 11:
					lst_tmp.add(2);
					break;
				case 12:
					lst_tmp.add(2);
					break;
				case 13:
					lst_tmp.add(3);
					break;
				default:
					System.out.println("ingredient non reconnu");
			}
		}
		// Conversion de la liste en un nouveau tableau
		int[] result = lst_tmp.stream().mapToInt(Integer::intValue).toArray();
		return result;
	}

	public  String[] get_name_liste_course() {
		// Conversion du tableau en liste pour permettre l'ajout d'éléments
		List<String> lst_tmp = new ArrayList<>();;
		for (Map.Entry<Integer, Integer> entry : lst_course.entrySet()) {
			int key = entry.getKey();
			switch (key) {
				case 1:
					lst_tmp.add("Tomate(s)");
					break;
				case 2:
					lst_tmp.add("Boeuf(s)");
					break;
				case 3:
					lst_tmp.add("Pain(s)");
					break;
				case 4:
					lst_tmp.add("Comte(s)");
					break;
				case 5:
					lst_tmp.add("Oignon(s)");
					break;
				case 6:
					lst_tmp.add("Enoki(s)");
					break;
				case 7:
					lst_tmp.add("Salade(s)");
					break;
				case 8:
					lst_tmp.add("Boudin(s)");
					break;
				case 9:
					lst_tmp.add("Pate(s)");
					break;
				case 10:
					lst_tmp.add("Citron(s)");
					break;
				case 11:
					lst_tmp.add("Cidre(s)");
					break;
				case 12:
					lst_tmp.add("Biere(s)");
					break;
				case 13:
					lst_tmp.add("Jus(s)");
					break;
				default:
					System.out.println("ingredient non reconnu");
			}
		}
		// Conversion de la liste en un nouveau tableau
		String[] result  = lst_tmp.toArray(new String[0]);
		return result;
	}

	public int get_prix_total(){
		  int tmp = 0;
		if(get_prix_liste_course().length > 0) {
			for (int i = 0; i < get_prix_liste_course().length; i++) {
				tmp += get_liste_course_quanitite()[i] * get_prix_liste_course()[i];
			}
		}
		return tmp;
	}
	public void ecrire_Map_Fichier(String nomFichier) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
			for (Map.Entry<Integer, Integer> entry : lst_course.entrySet()) {
				writer.write(entry.getKey() + ":" + entry.getValue());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void confirme_liste_course(stock stock_tmp) {
		ecrire_Map_Fichier("Liste.txt");
		for (Map.Entry<Integer, Integer> entry : lst_course.entrySet()) {
			entry.getKey();
			stock_tmp.add_quantities(entry.getKey(),entry.getValue());
		}
		lst_course.clear();
	}

	public void clear_liste_course(){
		lst_course.clear();
	}

	public Map<Integer, Integer> getLst_course_automatique() {
		return lst_course_automatique;
	}

	// Méthode pour ajouter une quantité à la liste de courses automatique
	public void add_quantities_automatique(int numero_ingredient, int quantite) {
		if (lst_course_automatique.containsKey(numero_ingredient)) {
			int quantite_Actuelle = lst_course_automatique.get(numero_ingredient);
			lst_course_automatique.put(numero_ingredient, quantite_Actuelle + quantite);
		} else {
			lst_course_automatique.put(numero_ingredient, quantite);
		}

	}
	public void ecrire_Map_Fichier_automatique(String nomFichier) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
			for (Map.Entry<Integer, Integer> entry : lst_course_automatique.entrySet()) {
				int numero_ingredient = entry.getKey();
				String nom_ingredient = getNomIngredient(numero_ingredient); // Utiliser la méthode existante
				int quantite = entry.getValue();
				System.out.println(entry.getKey());
				// Écrire dans le fichier le nom suivi de la quantité
				writer.write(nom_ingredient + ":" + quantite);
				writer.newLine();
			}
			lst_course_automatique.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getNomIngredient(int numero_ingredient) {
		switch (numero_ingredient) {
			case 1:
				return "tomate";
			case 2:
				return "boeuf";
			case 3:
				return "pain";
			case 4:
				return "frommage";
			case 5:
				return "oignon";
			case 6:
				return "champignon";
			case 7:
				return "salades";
			case 8:
				return "saucisse";
			case 9:
				return "pate a pizza";
			case 10:
				return "limonade";
			case 11:
				return "cidre doux";
			case 12:
				return "biere sans alcool";
			case 13:
				return "jus de fruits";
			default:
				System.out.println("Ingrédient non reconnu");
				return "";
		}
	}
}
