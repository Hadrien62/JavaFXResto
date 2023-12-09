/*
10: Limonade: 4€
11: Cidre doux: 5€
12: Bière sans alcool: 5€
13: Jus de fruit: 1€
14: Verre d’eau: Gratuit
*/
public class Boisson extends Produit {
	private int num_ingredient_boisson;

	public Boisson(int num_boisson) {
		switch (num_boisson) {
			case 12:
				this.num_ingredient_boisson = 10;
				setPrix(4);
				setNom("Limonade");
				setTemps_prep(2.0);
				break;
			case 13:
				this.num_ingredient_boisson = 11;
				setPrix(5);
				setNom("Cidre doux");
				setTemps_prep(2.0);
				break;
			case 14:
				this.num_ingredient_boisson = 12;
				setPrix(5);
				setNom("Bière sans alcool");
				setTemps_prep(2.0);
				break;
			case 15:
				this.num_ingredient_boisson = 13;
				setPrix(1);
				setNom("Jus de fruit");
				setTemps_prep(2.0);
				break;
			case 16:
				this.num_ingredient_boisson = 14;
				setPrix(0);
				setNom("Verre d’eau");
				setTemps_prep(2.0);
				break;
		}


	}

	public int getNum_boisson() {
		return num_ingredient_boisson;
	}

	public void setboisson(int num_boisson) {


	}
}
