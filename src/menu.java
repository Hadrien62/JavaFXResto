import java.util.List;
import java.util.ArrayList;

public class menu {
	private List<Plats> lst_plats;
	private List<Boisson> lst_boissons;
	public menu() {
		List<Boisson> tmp_lst_boissons = new ArrayList<>();
		List<Plats> tmp_lst_plats = new ArrayList<>();
		for(int i = 1; i<=11; i++) {
			Plats tmp_plat = new Plats(i);
			tmp_lst_plats.add(tmp_plat);
		}
		for(int i = 12; i<=16; i++) {
			Boisson tmp_boisson = new Boisson(i);
			tmp_lst_boissons.add(tmp_boisson);
		}
		this.lst_boissons = tmp_lst_boissons;
		this.lst_plats = tmp_lst_plats;
	}
	public List<Plats> getLst_plats() {
		return lst_plats;
	}
	public void setLst_plats(List<Plats> lst_plats) {
		this.lst_plats = lst_plats;
	}
	
	public List<Boisson> getLst_boissons() {
		return lst_boissons;
	}
	public void setLst_boissons(List<Boisson> lst_boissons) {
			this.lst_boissons = lst_boissons;
	}
	public void actualiser_menu(stock tmp_stock) {
		List<Boisson> tmp_lst_boissons = new ArrayList<>();
		List<Plats> tmp_lst_plats = new ArrayList<>();
		for(int i = 1; i<=11; i++) {
			Plats tmp_plat = new Plats(i);
			if(tmp_plat.check_stock(tmp_stock)) {
				tmp_lst_plats.add(tmp_plat);
			}
			
		}
		for(int i = 10; i<=14; i++) {
			Boisson tmp_boisson = new Boisson(i+2);
			if(i < 14) {
				if (tmp_stock.check_ingredient(i, 10)) {
					tmp_lst_boissons.add(tmp_boisson);
				}
			}
		}
		this.lst_boissons = tmp_lst_boissons;
		this.lst_plats = tmp_lst_plats;
	}
}

