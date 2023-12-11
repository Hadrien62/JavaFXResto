public class Boisson extends Produit {
	private int num_ingredient_boisson;
	public Boisson(int num_boisson){
		switch (num_boisson) {
			case 12:
				this.num_ingredient_boisson = 10;
				setPrix(5);
				setNom("Limonade");
				setTemps_prep(2.0);
				setNum_produit(12);
				break;
			case 13:
				this.num_ingredient_boisson = 11;
				setPrix(5);
				setNom("Cidre");
				setTemps_prep(2.0);
				setNum_produit(13);
				break;
			case 14:
				this.num_ingredient_boisson = 12;
				setPrix(5);
				setNom("Bière");
				setTemps_prep(2.0);
				setNum_produit(14);
				break;
			case 15:
				this.num_ingredient_boisson = 13;
				setPrix(1);
				setNom("Jus");
				setTemps_prep(2.0);
				setNum_produit(15);
				break;
			case 16:
				this.num_ingredient_boisson = 14;
				setPrix(0);
				setNom("Verre d’eau");
				setTemps_prep(2.0);
				setNum_produit(16);
				break;
		}
	}

	public int getNum_boisson() {
		return num_ingredient_boisson;
	}
	public void setboisson(int num_boisson) {
	}
	public void removestock(stock tmp_stock, ListeCourse tmp_liste) {
		tmp_stock.remove_quantities(num_ingredient_boisson, 1);
		tmp_liste.add_quantities_automatique(num_ingredient_boisson, 1);
	}
}
