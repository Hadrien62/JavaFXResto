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

					//manque des images ----------------------------
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
	@SuppressWarnings("null")
	public  int[] get_prix_liste_course() {

		// Conversion du tableau en liste pour permettre l'ajout d'éléments

		List<Integer> lst_tmp = null;


		for (Map.Entry<Integer, Integer> entry : lst_course.entrySet()) {
			int key = entry.getKey();
			switch (key) {
				case 1:
					lst_tmp.add(10);
					break;
				case 2:
					lst_tmp.add(20);
					break;
				case 3:
					lst_tmp.add(30);
					break;
				case 4:
					lst_tmp.add(15);
					break;
				case 5:
					lst_tmp.add(25);
					break;
				case 6:
					lst_tmp.add(35);
					break;
				case 7:
					lst_tmp.add(12);
					break;
				case 8:
					lst_tmp.add(22);
					break;
				case 9:
					lst_tmp.add(32);
					break;
				case 10:
					lst_tmp.add(18);
					break;
				case 11:
					lst_tmp.add(28);
					break;
				case 12:
					lst_tmp.add(38);
					break;
				case 13:
					lst_tmp.add(14);
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
					lst_tmp.add("tomate");
					break;
				case 2:
					lst_tmp.add("boeuf");
					break;
				case 3:
					lst_tmp.add("pain");
					break;
				case 4:
					lst_tmp.add("frommage");
					break;
				case 5:
					lst_tmp.add("oignon");
					break;
				case 6:
					lst_tmp.add("champignon");
					break;
				case 7:
					lst_tmp.add("salades");
					break;
				case 8:
					lst_tmp.add("saucise");
					break;
				case 9:
					lst_tmp.add("pate a pizza");
					break;
				case 10:
					lst_tmp.add("limonade");
					break;
				case 11:
					lst_tmp.add("cidre doux");
					break;
				case 12:
					lst_tmp.add("biere sans alcool");
					break;
				case 13:
					lst_tmp.add("jus de fruits");
					break;
				default:
					System.out.println("ingredient non reconnu");
			}
		}
		// Conversion de la liste en un nouveau tableau
		String[] result  = lst_tmp.toArray(new String[0]);
		return result;
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
				writer.write(entry.getKey() + ":" + entry.getValue());
				writer.newLine();
			}
			lst_course_automatique.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
